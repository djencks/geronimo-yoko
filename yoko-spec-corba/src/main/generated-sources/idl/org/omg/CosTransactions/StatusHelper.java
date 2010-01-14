package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/StatusHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/


// DATATYPES
abstract public class StatusHelper
{
  private static String  _id = "IDL:CosTransactions/Status:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CosTransactions.Status that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CosTransactions.Status extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (org.omg.CosTransactions.StatusHelper.id (), "Status", new String[] { "StatusActive", "StatusMarkedRollback", "StatusPrepared", "StatusCommitted", "StatusRolledBack", "StatusUnknown", "StatusNoTransaction", "StatusPreparing", "StatusCommitting", "StatusRollingBack"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.CosTransactions.Status read (org.omg.CORBA.portable.InputStream istream)
  {
    return org.omg.CosTransactions.Status.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CosTransactions.Status value)
  {
    ostream.write_long (value.value ());
  }

}
