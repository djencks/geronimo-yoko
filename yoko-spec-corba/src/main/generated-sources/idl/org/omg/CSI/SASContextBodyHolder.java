package org.omg.CSI;

/**
* org/omg/CSI/SASContextBodyHolder.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:58 AM PST
*/

public final class SASContextBodyHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CSI.SASContextBody value = null;

  public SASContextBodyHolder ()
  {
  }

  public SASContextBodyHolder (org.omg.CSI.SASContextBody initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.CSI.SASContextBodyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.CSI.SASContextBodyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.CSI.SASContextBodyHelper.type ();
  }

}
