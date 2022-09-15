import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EngineException;
import py4j.GatewayServer;

public class EntryPoint {
	private Birt4Py birt;
	public EntryPoint(){
		try{
			birt  = new Birt4Py();
		}catch(BirtException e){
			e.printStackTrace();
		}
	}
	public Birt4Py getStack(String param){
		try{
			birt.executeReport(param);
          }
		catch(EngineException e){
			e.printStackTrace();
          }
		return birt;
	}
	public static void main(String[] args){
		GatewayServer gatewayServer = new GatewayServer(new EntryPoint());
		gatewayServer.start();
		System.out.println("Birt4Py listener started!");
      }
}