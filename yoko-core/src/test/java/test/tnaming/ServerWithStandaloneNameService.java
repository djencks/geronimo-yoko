package test.tnaming;

import java.util.Properties;

import org.apache.yoko.orb.CosNaming.tnaming.TransientNameService;

public class ServerWithStandaloneNameService {
    public static final String REF_FILE_NAME = ServerWithStandaloneNameService.class.getName() + ".ref";
    public static void main(String args[]) throws Exception {
        try (TransientNameService service = new TransientNameService("localhost", Util.NS_PORT)) {
            System.out.println("Starting transient name service");
            service.run();
            System.out.println("Transient name service started");
            Properties props = new Properties();
            props.put("yoko.orb.oa.endpoint", "iiop --host localhost --port " + (Util.NS_PORT+1));
            props.put("org.omg.CORBA.ORBClass", "org.apache.yoko.orb.CORBA.ORB");
            props.put("org.omg.CORBA.ORBSingletonClass", "org.apache.yoko.orb.CORBA.ORBSingleton");
            
            try (Server s = new Server(props, "-ORBInitRef", "NameService=" + Util.NS_LOC)) {
                s.run(REF_FILE_NAME);
            }
        }
    }
}
