package org.omg.CosNaming.NamingContextExtPackage;

/**
* org/omg/CosNaming/NamingContextExtPackage/InvalidAddressHolder.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:58 AM PST
*/

public final class InvalidAddressHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CosNaming.NamingContextExtPackage.InvalidAddress value = null;

  public InvalidAddressHolder ()
  {
  }

  public InvalidAddressHolder (org.omg.CosNaming.NamingContextExtPackage.InvalidAddress initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.CosNaming.NamingContextExtPackage.InvalidAddressHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.CosNaming.NamingContextExtPackage.InvalidAddressHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.CosNaming.NamingContextExtPackage.InvalidAddressHelper.type ();
  }

}
