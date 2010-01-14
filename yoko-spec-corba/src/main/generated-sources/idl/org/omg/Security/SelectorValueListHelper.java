package org.omg.Security;


/**
* org/omg/Security/SelectorValueListHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:58 AM PST
*/

abstract public class SelectorValueListHelper
{
  private static String  _id = "IDL:omg.org/Security/SelectorValueList:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.Security.SelectorValue[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.Security.SelectorValue[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.Security.SelectorValueHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (org.omg.Security.SelectorValueListHelper.id (), "SelectorValueList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.Security.SelectorValue[] read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.Security.SelectorValue value[] = null;
    int _len0 = istream.read_long ();
    value = new org.omg.Security.SelectorValue[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = org.omg.Security.SelectorValueHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.Security.SelectorValue[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      org.omg.Security.SelectorValueHelper.write (ostream, value[_i0]);
  }

}
