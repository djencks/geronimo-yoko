package org.omg.Security;


/**
* org/omg/Security/OpaqueHolder.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:58 AM PST
*/

public final class OpaqueHolder implements org.omg.CORBA.portable.Streamable
{
  public byte value[] = null;

  public OpaqueHolder ()
  {
  }

  public OpaqueHolder (byte[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.Security.OpaqueHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.Security.OpaqueHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.Security.OpaqueHelper.type ();
  }

}
