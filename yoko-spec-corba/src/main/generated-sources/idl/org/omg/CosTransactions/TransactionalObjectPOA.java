package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/TransactionalObjectPOA.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/


// TransactionalObject has been deprecated. See 10.3.10.
public abstract class TransactionalObjectPOA extends org.omg.PortableServer.Servant
 implements org.omg.CosTransactions.TransactionalObjectOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CosTransactions/TransactionalObject:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public TransactionalObject _this() 
  {
    return TransactionalObjectHelper.narrow(
    super._this_object());
  }

  public TransactionalObject _this(org.omg.CORBA.ORB orb) 
  {
    return TransactionalObjectHelper.narrow(
    super._this_object(orb));
  }


} // class TransactionalObjectPOA
