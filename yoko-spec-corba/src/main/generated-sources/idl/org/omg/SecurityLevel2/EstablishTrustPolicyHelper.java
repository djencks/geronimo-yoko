package org.omg.SecurityLevel2;


/**
* org/omg/SecurityLevel2/EstablishTrustPolicyHelper.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/


/* */
abstract public class EstablishTrustPolicyHelper
{
  private static String  _id = "IDL:SecurityLevel2/EstablishTrustPolicy:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.SecurityLevel2.EstablishTrustPolicy that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.SecurityLevel2.EstablishTrustPolicy extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (org.omg.SecurityLevel2.EstablishTrustPolicyHelper.id (), "EstablishTrustPolicy");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.SecurityLevel2.EstablishTrustPolicy read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_EstablishTrustPolicyStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.SecurityLevel2.EstablishTrustPolicy value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static org.omg.SecurityLevel2.EstablishTrustPolicy narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof org.omg.SecurityLevel2.EstablishTrustPolicy)
      return (org.omg.SecurityLevel2.EstablishTrustPolicy)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      org.omg.SecurityLevel2._EstablishTrustPolicyStub stub = new org.omg.SecurityLevel2._EstablishTrustPolicyStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static org.omg.SecurityLevel2.EstablishTrustPolicy unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof org.omg.SecurityLevel2.EstablishTrustPolicy)
      return (org.omg.SecurityLevel2.EstablishTrustPolicy)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      org.omg.SecurityLevel2._EstablishTrustPolicyStub stub = new org.omg.SecurityLevel2._EstablishTrustPolicyStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
