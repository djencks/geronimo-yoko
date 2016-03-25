/**
*
* Licensed to the Apache Software Foundation (ASF) under one or more
*  contributor license agreements.  See the NOTICE file distributed with
*  this work for additional information regarding copyright ownership.
*  The ASF licenses this file to You under the Apache License, Version 2.0
*  (the "License"); you may not use this file except in compliance with
*  the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

package org.apache.yoko.rmi.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.logging.Logger;

import org.omg.CORBA.MARSHAL;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ValueMember;
import org.omg.CORBA.portable.InputStream;

abstract class ArrayDescriptor extends ValueDescriptor {
    protected int order;

    protected Class basicType;

    protected Class elementType;
    // repository ID for the contained elements
    private volatile String _elementRepid = null;

    @Override
    protected String genRepId() {
        if (elementType.isPrimitive() || elementType == Object.class)
            return String.format("RMI:%s:%016X", _java_class.getName(), 0);

        TypeDescriptor desc = repo.getDescriptor(elementType);
        String elemRep = desc.getRepositoryID();
        String hash = elemRep.substring(elemRep.indexOf(':', 4));
        return String.format("RMI:%s:%s", _java_class.getName(), hash);
    }

    private final String genElemRepId() {
        if (elementType.isPrimitive() || elementType == Object.class) {
            // use the descriptor type past the array type marker
            return String.format("RMI:%s:%016X", _java_class.getName().substring(1), 0);
        }
        return repo.getDescriptor(elementType).getRepositoryID();
    }

    public String getElementRepositoryID() {
        if (_elementRepid == null) _elementRepid = genElemRepId();
        return _elementRepid;
    }

    protected ArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, rep);
        logger.fine("Creating an array descriptor for type " + type.getName() + " holding elements of " + elemType.getName()); 
        this.elementType = elemType;

        order = 1;
        basicType = elemType;
        while (basicType.isArray()) {
            basicType = basicType.getComponentType();
            order += 1;
        }
    }

    public String getIDLName() {
        StringBuffer sb = new StringBuffer("org_omg_boxedRMI_");

        TypeDescriptor desc = repo.getDescriptor(basicType);
        
        // The logic that looks for the last "_" fails when this is a 
        // long_long primitive type.  The primitive types have a "" package 
        // name, so check those first.  If it's not one of the primitives, 
        // then we can safely split using the last index position.
        String pkgName = desc.getPackageName(); 
        if (pkgName.length() == 0) {
            sb.append("seq");
            sb.append(order);
            sb.append('_');
            sb.append(desc.getTypeName());
        }
        else {
            String elemName = desc.getIDLName();

            int idx = elemName.lastIndexOf('_');

            pkgName = elemName.substring(0, idx + 1);
            String elmName = elemName.substring(idx + 1);

            sb.append(pkgName);

            sb.append("seq");
            sb.append(order);
            sb.append('_');

            sb.append(elmName);
        }

        return sb.toString();
    }

    static ArrayDescriptor get(final Class type, TypeRepository rep) {
        logger.fine("retrieving an array descriptor for class " + type.getName()); 
        if (!type.isArray()) {
            throw new IllegalArgumentException("type is not an array");
        }

        Class elemType = type.getComponentType();

        if (elemType.isPrimitive()) {

            if (elemType == Boolean.TYPE) {
                return new BooleanArrayDescriptor(type, elemType, rep);
            } else if (elemType == Byte.TYPE) {
                return new ByteArrayDescriptor(type, elemType, rep);
            } else if (elemType == Character.TYPE) {
                return new CharArrayDescriptor(type, elemType, rep);
            } else if (elemType == Short.TYPE) {
                return new ShortArrayDescriptor(type, elemType, rep);
            } else if (elemType == Integer.TYPE) {
                return new IntArrayDescriptor(type, elemType, rep);
            } else if (elemType == Long.TYPE) {
                return new LongArrayDescriptor(type, elemType, rep);
            } else if (elemType == Float.TYPE) {
                return new FloatArrayDescriptor(type, elemType, rep);
            } else if (elemType == Double.TYPE) {
                return new DoubleArrayDescriptor(type, elemType, rep);
            } else {
                throw new RuntimeException("unknown array type " + type);
            }
        }
        if (java.io.Serializable.class.equals(elemType) || 
                java.io.Externalizable.class.equals(elemType) || Object.class.equals(elemType)) {
            return new ObjectArrayDescriptor(type, elemType, rep);

        } else if (java.io.Serializable.class.isAssignableFrom(elemType)) {
            return new ValueArrayDescriptor(type, elemType, rep);

        } else if (java.rmi.Remote.class.isAssignableFrom(elemType)) {
            return new RemoteArrayDescriptor(type, elemType, rep);

        } else {
            return new AbstractObjectArrayDescriptor(type, elemType, rep);
        }

    }

    /**
     * Read an instance of this value from a CDR stream. Overridden to provide a
     * specific type
     */
    public Object read(org.omg.CORBA.portable.InputStream in) {
        org.omg.CORBA_2_3.portable.InputStream _in = (org.omg.CORBA_2_3.portable.InputStream) in;
        logger.fine("Reading an array value with repository id " + getRepositoryID() + " java class is " + _java_class);
        
        // if we have a resolved class, read using that, otherwise fall back on the 
        // repository id. 
        return ((null == _java_class) ? _in.read_value(getRepositoryID()) : _in.read_value(_java_class));
    }

    /** Write an instance of this value to a CDR stream */
    public void write(org.omg.CORBA.portable.OutputStream out, Object value) {
        org.omg.CORBA_2_3.portable.OutputStream _out = (org.omg.CORBA_2_3.portable.OutputStream) out;

        _out.write_value((java.io.Serializable)value, getRepositoryID());
    }

    org.omg.CORBA.ValueMember[] getValueMembers() {

        if (_value_members == null) {

            _value_members = new org.omg.CORBA.ValueMember[1];

            TypeDescriptor elemDesc = repo.getDescriptor(elementType);

            String elemRepID = elemDesc.getRepositoryID();

            ORB orb = org.omg.CORBA.ORB.init();
            TypeCode memberTC = orb.create_sequence_tc(0, elemDesc
                    .getTypeCode());

            _value_members[0] = new ValueMember("", // member has no name!
                    elemRepID, this.getRepositoryID(), "1.0", memberTC, null,
                    (short) 1);
            // public
        }

        return _value_members;
    }

    void addDependencies(java.util.Set classes) {
        repo.getDescriptor(basicType).addDependencies(classes);
    }

}

class ObjectArrayDescriptor extends ArrayDescriptor {
    static Logger logger = Logger.getLogger(ArrayDescriptor.class.getName());

    ObjectArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        // System.out.println ("ObjectArrayDescriptor::writeValue
        // "+getRepositoryID ());

        Object[] arr = (Object[]) value;
        out.write_long(arr.length);

        logger.finer("writing " + _java_class.getName() + " size="
                + arr.length);

        for (int i = 0; i < arr.length; i++) {
            javax.rmi.CORBA.Util.writeAny(out, arr[i]); 
        }
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        try {
            ObjectReader reader = makeCorbaObjectReader(in, offsetMap, null);

            int length = reader.readInt();
            Object[] arr = (Object[]) Array.newInstance(elementType, length);

            offsetMap.put(key, arr);

            logger.fine("reading " + _java_class.getName() + " size="
                    + arr.length);

            for (int i = 0; i < length; i++) {
                try {
                    arr[i] = reader.readAny();
                    if (arr[i] != null) {
                        logger.finer("Array item " + i + " is of type " + arr[i].getClass().getName()); 
                    }
                    else {
                        logger.finer("Array item " + i + " is null"); 
                    }
                } catch (org.omg.CORBA.portable.IndirectionException ex) {
                    arr[i] = offsetMap.get(new Integer(ex.offset));
                    // reader.addValueBox (ex.offset, new ArrayBox (i, arr));
                }
            }
            return (java.io.Serializable) arr;

        } catch (java.io.IOException ex) {
            throw (MARSHAL)new MARSHAL(ex.getMessage()).initCause(ex);
        }

    }

    Object copyObject(Object value, CopyState state) {
        final Object[] orig = (Object[]) value;
        final Object[] result = new Object[orig.length];
        state.put(value, result);

        for (int i = 0; i < orig.length; i++) {
            try {
                result[i] = state.copy(orig[i]);
            } catch (CopyRecursionException e) {
                final int idx = i;

                state.registerRecursion(new CopyRecursionResolver(orig[i]) {
                    public void resolve(Object value) {
                        result[idx] = value;
                    }
                });
            }
        }

        return result;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        Object[] arr = (Object[]) val;
        TypeDescriptor desc = repo.getDescriptor(elementType);
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            desc.print(pw, recurse, arr[i]);
        }
    }

}

class RemoteArrayDescriptor extends ArrayDescriptor {
    RemoteArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        // System.out.println ("RemoteArrayDescriptor::writeValue
        // "+getRepositoryID ());

        Object[] arr = (Object[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            javax.rmi.CORBA.Util.writeRemoteObject(out, arr[i]);
        }
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        try {
            ObjectReader reader = makeCorbaObjectReader(in, offsetMap, null);

            int length = reader.readInt();
            Object[] arr = (Object[]) Array.newInstance(elementType, length);
            offsetMap.put(key, arr);

            for (int i = 0; i < length; i++) {
                try {
                    arr[i] = reader.readRemoteObject(elementType);
                } catch (org.omg.CORBA.portable.IndirectionException ex) {
                    arr[i] = offsetMap.get(new Integer(ex.offset));
                    // reader.addValueBox (ex.offset, new ArrayBox (i, arr));
                }
            }

            return (java.io.Serializable) arr;

        } catch (java.io.IOException ex) {
            throw (MARSHAL)new MARSHAL(ex.getMessage()).initCause(ex);
        }

    }

    Object copyObject(Object value, CopyState state) {
        final Object[] orig = (Object[]) value;
        final Object[] result = (Object[]) Array.newInstance(elementType,
                orig.length);

        state.put(value, result);
        for (int i = 0; i < orig.length; i++) {
            try {
                result[i] = state.copy(orig[i]);
            } catch (CopyRecursionException e) {
                final int idx = i;

                state.registerRecursion(new CopyRecursionResolver(orig[i]) {
                    public void resolve(Object value) {
                        result[idx] = value;
                    }
                });
            }
        }

        return result;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        Object[] arr = (Object[]) val;
        TypeDescriptor desc = repo.getDescriptor(elementType);
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            desc.print(pw, recurse, arr[i]);
        }
    }
}

class ValueArrayDescriptor extends ArrayDescriptor {

    ValueArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        // System.out.println ("ValueArrayDescriptor::writeValue
        // "+getRepositoryID ());
        Object[] arr = (Object[]) value;
        out.write_long(arr.length);
        java.io.Serializable[] sarr = (java.io.Serializable[]) arr;
        org.omg.CORBA_2_3.portable.OutputStream _out = (org.omg.CORBA_2_3.portable.OutputStream) out;
        for (int i = 0; i < sarr.length; i++) {
            _out.write_value(sarr[i], getElementRepositoryID());
        }
    }

    public Serializable readValue(InputStream in, Map offsetMap, Integer key) {
        final int length = in.read_long();
        Object[] arr = (Object[]) Array.newInstance(elementType, length);
        offsetMap.put(key, arr);

        final org.omg.CORBA_2_3.portable.InputStream _in = (org.omg.CORBA_2_3.portable.InputStream) in;
        for (int i = 0; i < length; i++) {
            try {
                arr[i] = _in.read_value(elementType);
            } catch (org.omg.CORBA.portable.IndirectionException ex) {
                arr[i] = offsetMap.get(new Integer(ex.offset));
            }
        }

        return arr;
    }

    Object copyObject(Object value, CopyState state) {
        Object[] orig = (Object[]) value;
        final Object[] result = (Object[]) Array.newInstance(value.getClass()
                .getComponentType(), orig.length);

        state.put(value, result);
        for (int i = 0; i < orig.length; i++) {
            try {
                result[i] = state.copy(orig[i]);
            } catch (CopyRecursionException e) {
                final int idx = i;

                state.registerRecursion(new CopyRecursionResolver(orig[i]) {
                    public void resolve(Object value) {
                        result[idx] = value;
                    }
                });
            }
        }

        return result;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        Object[] arr = (Object[]) val;
        TypeDescriptor desc = repo.getDescriptor(elementType);
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            desc.print(pw, recurse, arr[i]);
        }
    }

}

class AbstractObjectArrayDescriptor extends ArrayDescriptor {
    AbstractObjectArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        // System.out.println ("AbstractObjectArrayDescriptor::writeValue
        // "+getRepositoryID ());

        Object[] arr = (Object[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            javax.rmi.CORBA.Util.writeAbstractObject(out, arr[i]);
        }
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        try {
            ObjectReader reader = makeCorbaObjectReader(in, offsetMap, null);

            int length = reader.readInt();
            Object[] arr = (Object[]) Array.newInstance(elementType, length);

            offsetMap.put(key, arr);

            for (int i = 0; i < length; i++) {
                try {
                    arr[i] = reader.readAbstractObject();
                } catch (org.omg.CORBA.portable.IndirectionException ex) {
                    arr[i] = offsetMap.get(new Integer(ex.offset));
                    // reader.addValueBox (ex.offset, new ArrayBox (i, arr));
                }
            }

            return (java.io.Serializable) arr;

        } catch (java.io.IOException ex) {
            throw (MARSHAL)new MARSHAL(ex.getMessage()).initCause(ex);
        }
    }

    Object copyObject(Object value, CopyState state) {
        final Object[] orig = (Object[]) value;
        final Object[] result = (Object[]) Array.newInstance(elementType,
                orig.length);

        state.put(value, result);
        for (int i = 0; i < orig.length; i++) {
            try {
                result[i] = state.copy(orig[i]);
            } catch (CopyRecursionException e) {
                final int idx = i;

                state.registerRecursion(new CopyRecursionResolver(orig[i]) {
                    public void resolve(Object value) {
                        result[idx] = value;
                    }
                });
            }
        }

        return result;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        Object[] arr = (Object[]) val;
        TypeDescriptor desc = repo.getDescriptor(elementType);
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            desc.print(pw, recurse, arr[i]);
        }
    }

}

class BooleanArrayDescriptor extends ArrayDescriptor {
    BooleanArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        boolean[] arr = new boolean[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_boolean();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        boolean[] arr = (boolean[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            out.write_boolean(arr[i]);
        }
    }

    Object copyObject(Object value, CopyState state) {
        if (((boolean[]) value).length == 0)
            return value;

        Object copy = ((boolean[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        boolean[] arr = (boolean[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }

}

class ByteArrayDescriptor extends ArrayDescriptor {

    ByteArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        byte[] arr = new byte[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_octet();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        byte[] arr = (byte[]) value;
        out.write_long(arr.length);

        out.write_octet_array(arr, 0, arr.length);

        // for (int i = 0; i < arr.length; i++) {
        // out.write_octet(arr[i]);
        // }
    }

    Object copyObject(Object value, CopyState state) {
        if (((byte[]) value).length == 0)
            return value;

        Object copy = ((byte[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        byte[] arr = (byte[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }
}

class CharArrayDescriptor extends ArrayDescriptor {
    CharArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        int len = in.read_long();
        char[] arr = new char[len];
        offsetMap.put(key, arr);
        in.read_wchar_array(arr, 0, len);
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        char[] arr = (char[]) value;
        out.write_long(arr.length);
        out.write_wchar_array(arr, 0, arr.length);
    }

    Object copyObject(Object value, CopyState state) {
        if (((char[]) value).length == 0)
            return value;

        Object copy = ((char[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        char[] arr = (char[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }
}

class ShortArrayDescriptor extends ArrayDescriptor {
    ShortArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        short[] arr = new short[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_short();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        short[] arr = (short[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            out.write_short(arr[i]);
        }
    }

    Object copyObject(Object value, CopyState state) {
        if (((short[]) value).length == 0)
            return value;

        Object copy = ((short[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        short[] arr = (short[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }
}

class IntArrayDescriptor extends ArrayDescriptor {
    IntArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        int[] arr = new int[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_long();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        int[] arr = (int[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            out.write_long(arr[i]);
        }
    }

    Object copyObject(Object value, CopyState state) {
        if (((int[]) value).length == 0)
            return value;

        Object copy = ((int[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        int[] arr = (int[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }
}

class LongArrayDescriptor extends ArrayDescriptor {
    LongArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        long[] arr = new long[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_longlong();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        long[] arr = (long[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            out.write_longlong(arr[i]);
        }
    }

    Object copyObject(Object value, CopyState state) {
        if (((long[]) value).length == 0)
            return value;

        Object copy = ((long[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        long[] arr = (long[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }

}

class FloatArrayDescriptor extends ArrayDescriptor {
    FloatArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        float[] arr = new float[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_float();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        float[] arr = (float[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            out.write_float(arr[i]);
        }
    }

    Object copyObject(Object value, CopyState state) {
        if (((float[]) value).length == 0)
            return value;

        Object copy = ((float[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        float[] arr = (float[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }
}

class DoubleArrayDescriptor extends ArrayDescriptor {
    DoubleArrayDescriptor(Class type, Class elemType, TypeRepository rep) {
        super(type, elemType, rep);
    }

    public java.io.Serializable readValue(
            org.omg.CORBA.portable.InputStream in, java.util.Map offsetMap,
            Integer key) {
        double[] arr = new double[in.read_long()];
        offsetMap.put(key, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.read_double();
        }
        return (java.io.Serializable) arr;
    }

    public void writeValue(org.omg.CORBA.portable.OutputStream out,
            java.io.Serializable value) {
        double[] arr = (double[]) value;
        out.write_long(arr.length);
        for (int i = 0; i < arr.length; i++) {
            out.write_double(arr[i]);
        }
    }

    Object copyObject(Object value, CopyState state) {
        if (((double[]) value).length == 0)
            return value;

        Object copy = ((double[]) value).clone();
        state.put(value, copy);
        return copy;
    }

    void printFields(java.io.PrintWriter pw, java.util.Map recurse, Object val) {
        double[] arr = (double[]) val;
        pw.print("length=" + arr.length + "; ");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                pw.print(", ");
            }
            pw.print(arr[i]);
        }
    }
}
