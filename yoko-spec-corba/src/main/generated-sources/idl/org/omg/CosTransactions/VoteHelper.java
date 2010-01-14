package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/VoteHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/

abstract public class VoteHelper
{
  private static String  _id = "IDL:CosTransactions/Vote:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CosTransactions.Vote that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CosTransactions.Vote extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (org.omg.CosTransactions.VoteHelper.id (), "Vote", new String[] { "VoteCommit", "VoteRollback", "VoteReadOnly"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.CosTransactions.Vote read (org.omg.CORBA.portable.InputStream istream)
  {
    return org.omg.CosTransactions.Vote.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CosTransactions.Vote value)
  {
    ostream.write_long (value.value ());
  }

}
