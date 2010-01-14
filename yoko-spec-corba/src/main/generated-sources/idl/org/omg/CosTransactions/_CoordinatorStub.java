package org.omg.CosTransactions;


/**
* org/omg/CosTransactions/_CoordinatorStub.java .
* Error reading Messages File.
* Error reading Messages File.
* Thursday, January 14, 2010 1:08:59 AM PST
*/

public class _CoordinatorStub extends org.omg.CORBA.portable.ObjectImpl implements org.omg.CosTransactions.Coordinator
{

  public org.omg.CosTransactions.Status get_status ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_status", true);
                $in = _invoke ($out);
                org.omg.CosTransactions.Status $result = org.omg.CosTransactions.StatusHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_status (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_status

  public org.omg.CosTransactions.Status get_parent_status ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_parent_status", true);
                $in = _invoke ($out);
                org.omg.CosTransactions.Status $result = org.omg.CosTransactions.StatusHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_parent_status (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_parent_status

  public org.omg.CosTransactions.Status get_top_level_status ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_top_level_status", true);
                $in = _invoke ($out);
                org.omg.CosTransactions.Status $result = org.omg.CosTransactions.StatusHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_top_level_status (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_top_level_status

  public boolean is_same_transaction (org.omg.CosTransactions.Coordinator tc)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("is_same_transaction", true);
                org.omg.CosTransactions.CoordinatorHelper.write ($out, tc);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return is_same_transaction (tc        );
            } finally {
                _releaseReply ($in);
            }
  } // is_same_transaction

  public boolean is_related_transaction (org.omg.CosTransactions.Coordinator tc)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("is_related_transaction", true);
                org.omg.CosTransactions.CoordinatorHelper.write ($out, tc);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return is_related_transaction (tc        );
            } finally {
                _releaseReply ($in);
            }
  } // is_related_transaction

  public boolean is_ancestor_transaction (org.omg.CosTransactions.Coordinator tc)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("is_ancestor_transaction", true);
                org.omg.CosTransactions.CoordinatorHelper.write ($out, tc);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return is_ancestor_transaction (tc        );
            } finally {
                _releaseReply ($in);
            }
  } // is_ancestor_transaction

  public boolean is_descendant_transaction (org.omg.CosTransactions.Coordinator tc)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("is_descendant_transaction", true);
                org.omg.CosTransactions.CoordinatorHelper.write ($out, tc);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return is_descendant_transaction (tc        );
            } finally {
                _releaseReply ($in);
            }
  } // is_descendant_transaction

  public boolean is_top_level_transaction ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("is_top_level_transaction", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return is_top_level_transaction (        );
            } finally {
                _releaseReply ($in);
            }
  } // is_top_level_transaction

  public int hash_transaction ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("hash_transaction", true);
                $in = _invoke ($out);
                int $result = $in.read_ulong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return hash_transaction (        );
            } finally {
                _releaseReply ($in);
            }
  } // hash_transaction

  public int hash_top_level_tran ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("hash_top_level_tran", true);
                $in = _invoke ($out);
                int $result = $in.read_ulong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return hash_top_level_tran (        );
            } finally {
                _releaseReply ($in);
            }
  } // hash_top_level_tran

  public org.omg.CosTransactions.RecoveryCoordinator register_resource (org.omg.CosTransactions.Resource r) throws org.omg.CosTransactions.Inactive
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("register_resource", true);
                org.omg.CosTransactions.ResourceHelper.write ($out, r);
                $in = _invoke ($out);
                org.omg.CosTransactions.RecoveryCoordinator $result = org.omg.CosTransactions.RecoveryCoordinatorHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:CosTransactions/Inactive:1.0"))
                    throw org.omg.CosTransactions.InactiveHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return register_resource (r        );
            } finally {
                _releaseReply ($in);
            }
  } // register_resource

  public void register_synchronization (org.omg.CosTransactions.Synchronization sync) throws org.omg.CosTransactions.Inactive, org.omg.CosTransactions.SynchronizationUnavailable
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("register_synchronization", true);
                org.omg.CosTransactions.SynchronizationHelper.write ($out, sync);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:CosTransactions/Inactive:1.0"))
                    throw org.omg.CosTransactions.InactiveHelper.read ($in);
                else if (_id.equals ("IDL:CosTransactions/SynchronizationUnavailable:1.0"))
                    throw org.omg.CosTransactions.SynchronizationUnavailableHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                register_synchronization (sync        );
            } finally {
                _releaseReply ($in);
            }
  } // register_synchronization

  public void register_subtran_aware (org.omg.CosTransactions.SubtransactionAwareResource r) throws org.omg.CosTransactions.Inactive, org.omg.CosTransactions.NotSubtransaction
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("register_subtran_aware", true);
                org.omg.CosTransactions.SubtransactionAwareResourceHelper.write ($out, r);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:CosTransactions/Inactive:1.0"))
                    throw org.omg.CosTransactions.InactiveHelper.read ($in);
                else if (_id.equals ("IDL:CosTransactions/NotSubtransaction:1.0"))
                    throw org.omg.CosTransactions.NotSubtransactionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                register_subtran_aware (r        );
            } finally {
                _releaseReply ($in);
            }
  } // register_subtran_aware

  public void rollback_only () throws org.omg.CosTransactions.Inactive
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("rollback_only", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:CosTransactions/Inactive:1.0"))
                    throw org.omg.CosTransactions.InactiveHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                rollback_only (        );
            } finally {
                _releaseReply ($in);
            }
  } // rollback_only

  public String get_transaction_name ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_transaction_name", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_transaction_name (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_transaction_name

  public org.omg.CosTransactions.Control create_subtransaction () throws org.omg.CosTransactions.SubtransactionsUnavailable, org.omg.CosTransactions.Inactive
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("create_subtransaction", true);
                $in = _invoke ($out);
                org.omg.CosTransactions.Control $result = org.omg.CosTransactions.ControlHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:CosTransactions/SubtransactionsUnavailable:1.0"))
                    throw org.omg.CosTransactions.SubtransactionsUnavailableHelper.read ($in);
                else if (_id.equals ("IDL:CosTransactions/Inactive:1.0"))
                    throw org.omg.CosTransactions.InactiveHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return create_subtransaction (        );
            } finally {
                _releaseReply ($in);
            }
  } // create_subtransaction

  public org.omg.CosTransactions.PropagationContext get_txcontext () throws org.omg.CosTransactions.Unavailable
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_txcontext", true);
                $in = _invoke ($out);
                org.omg.CosTransactions.PropagationContext $result = org.omg.CosTransactions.PropagationContextHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:CosTransactions/Unavailable:1.0"))
                    throw org.omg.CosTransactions.UnavailableHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_txcontext (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_txcontext

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CosTransactions/Coordinator:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _CoordinatorStub
