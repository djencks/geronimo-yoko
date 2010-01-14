package org.omg.Security;


/**
* org/omg/Security/MechandOptionsHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:58 AM PST
*/

abstract public class MechandOptionsHelper
{
  private static String  _id = "IDL:omg.org/Security/MechandOptions:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.Security.MechandOptions that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.Security.MechandOptions extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (org.omg.Security.MechanismTypeHelper.id (), "MechanismType", _tcOf_members0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "mechanism_type",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (org.omg.Security.AssociationOptionsHelper.id (), "AssociationOptions", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "options_supported",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (org.omg.Security.MechandOptionsHelper.id (), "MechandOptions", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.Security.MechandOptions read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.Security.MechandOptions value = new org.omg.Security.MechandOptions ();
    value.mechanism_type = istream.read_string ();
    value.options_supported = istream.read_ushort ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.Security.MechandOptions value)
  {
    ostream.write_string (value.mechanism_type);
    ostream.write_ushort (value.options_supported);
  }

}
