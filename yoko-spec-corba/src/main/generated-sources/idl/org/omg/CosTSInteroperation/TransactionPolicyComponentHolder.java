package org.omg.CosTSInteroperation;

/**
* org/omg/CosTSInteroperation/TransactionPolicyComponentHolder.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:58 AM PST
*/

public final class TransactionPolicyComponentHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CosTSInteroperation.TransactionPolicyComponent value = null;

  public TransactionPolicyComponentHolder ()
  {
  }

  public TransactionPolicyComponentHolder (org.omg.CosTSInteroperation.TransactionPolicyComponent initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = org.omg.CosTSInteroperation.TransactionPolicyComponentHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    org.omg.CosTSInteroperation.TransactionPolicyComponentHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return org.omg.CosTSInteroperation.TransactionPolicyComponentHelper.type ();
  }

}
