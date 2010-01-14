package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/ControlHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/

abstract public class ControlHelper
{
  private static String  _id = "IDL:CosTransactions/Control:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CosTransactions.Control that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CosTransactions.Control extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (org.omg.CosTransactions.ControlHelper.id (), "Control");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.CosTransactions.Control read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ControlStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CosTransactions.Control value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static org.omg.CosTransactions.Control narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof org.omg.CosTransactions.Control)
      return (org.omg.CosTransactions.Control)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      org.omg.CosTransactions._ControlStub stub = new org.omg.CosTransactions._ControlStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static org.omg.CosTransactions.Control unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof org.omg.CosTransactions.Control)
      return (org.omg.CosTransactions.Control)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      org.omg.CosTransactions._ControlStub stub = new org.omg.CosTransactions._ControlStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
