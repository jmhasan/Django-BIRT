package org.birtexchange.django;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
public class Birt4Py{
	private static String report;
	private static IReportEngine engine =null;
	private static EngineConfig  config =null;
	public static String getReport(){
		return report;
	}
	public Birt4Py() throws BirtException{//start up Platform
		  config =new EngineConfig();
	  	  config.setLogConfig("./logs", java.util.logging.Level.FINEST);
	  	  Platform.startup( config );//create new Report
	  	  EngineIReportEngineFactory factory =(IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
		  engine = factory.createReportEngine( config );
		  }
	public void executeReport(String param)throws EngineException{
		try{
			//open the report design
			IReportRunnable design = null;
			design = engine.openReportDesign("/reports/test.rptdesign");//create Run and Render TaskIRun And Render
			Task task = engine.createRunAndRenderTask(design);//pass necessary parameters
			task.setParameterValue("Country", param);
			System.out.println("Countery Name"+param)
			task.validateParameters();//set render options including output typeHTMLRender
			Option options =new HTMLRenderOption();
			ByteArrayOutputStream outs = new ByteArrayOutputStream();
			options.setOutputStream(outs);
			options.setImageHandler(new HTMLServerImageHandler());
			options.setBaseImageURL("http://localhost/images");
			options.setImageDirectory("/var/www/images");
			options.setEmbeddable(true);
			options.setOutputFormat("html");
			task.setRenderOption(options);//run taskString output;
			task.run();
			output = outs.toString();
			task.close();
			report = output;

			}catch(Exception ex){
				  ex.printStackTrace();
			}
		}
	}