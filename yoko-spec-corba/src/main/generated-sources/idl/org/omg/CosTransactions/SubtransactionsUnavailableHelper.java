package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/SubtransactionsUnavailableHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/

abstract public class SubtransactionsUnavailableHelper
{
  private static String  _id = "IDL:CosTransactions/SubtransactionsUnavailable:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CosTransactions.SubtransactionsUnavailable that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CosTransactions.SubtransactionsUnavailable extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [0];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (org.omg.CosTransactions.SubtransactionsUnavailableHelper.id (), "SubtransactionsUnavailable", _members0);
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

  public static org.omg.CosTransactions.SubtransactionsUnavailable read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.CosTransactions.SubtransactionsUnavailable value = new org.omg.CosTransactions.SubtransactionsUnavailable ();
    // read and discard the repository ID
    istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CosTransactions.SubtransactionsUnavailable value)
  {
    // write the repository ID
    ostream.write_string (id ());
  }

}
