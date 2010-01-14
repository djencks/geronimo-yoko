package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/TransIdentityHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/

abstract public class TransIdentityHelper
{
  private static String  _id = "IDL:CosTransactions/TransIdentity:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CosTransactions.TransIdentity that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CosTransactions.TransIdentity extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CosTransactions.CoordinatorHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "coord",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CosTransactions.TerminatorHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "term",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CosTransactions.otid_tHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "otid",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (org.omg.CosTransactions.TransIdentityHelper.id (), "TransIdentity", _members0);
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

  public static org.omg.CosTransactions.TransIdentity read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.CosTransactions.TransIdentity value = new org.omg.CosTransactions.TransIdentity ();
    value.coord = org.omg.CosTransactions.CoordinatorHelper.read (istream);
    value.term = org.omg.CosTransactions.TerminatorHelper.read (istream);
    value.otid = org.omg.CosTransactions.otid_tHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CosTransactions.TransIdentity value)
  {
    org.omg.CosTransactions.CoordinatorHelper.write (ostream, value.coord);
    org.omg.CosTransactions.TerminatorHelper.write (ostream, value.term);
    org.omg.CosTransactions.otid_tHelper.write (ostream, value.otid);
  }

}
