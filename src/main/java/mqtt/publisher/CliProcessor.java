package mqtt.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
//import org.sonarsource.scanner.api.internal.shaded.minimaljson.JsonObject;
/**
 * The CLI processor that processes the CLI
 * arguments passed to the socket-client
 * application.
 *
 * @author manh Ton Nguyen
 */
public class CliProcessor {
	
	/** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CliProcessor.class);
    /** The help message. */
    private static final String HELP_MSG = "socket-client.sh [OPTIONS] [MESSAGE]";
    /** The one and only instance of CLI processor. */
    private static CliProcessor instance;
    /** The CLI parameters store object. */
    private CliParameters cliParameters;
    /**
     * The static getter for the CLI processor instance.
     *
     * @return The CLI processor instance.
     */
    public static CliProcessor getInstance() {
        if (instance == null)
            instance = new CliProcessor();
        return instance;
    }
    /////
    public void parseCliOptions(String[] args) {
    	 // Command line options.
    	Options options = createCliOptions();
    	// Command line parser.
        CommandLineParser parser = new DefaultParser();
      //save to send
        //Map<String,String> map = new HashMap<String, String>();
        List<String> dataToSend = new ArrayList<>() ;
        try {
            // Parse the command line arguments.
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("h")) {
                printHelp(options);
                System.exit(Defaults.EXIT_CODE_SUCCESS);
            }
            
            if (line.hasOption("b")) {
                this.cliParameters.setBrokerAddress(line.getOptionValue('b'));
            }
            if (line.hasOption("p")) {
                this.cliParameters.setBrokerPort(line.getOptionValue('p'));
            }
            if (line.hasOption("P")) {
                this.cliParameters.setBrokerProtocol(line.getOptionValue('P'));
            }
            if (line.hasOption("t")) {
                this.cliParameters.setTopic(line.getOptionValue('t'));
            }
            if (line.hasOption("t")){
                //map.put("temparatur",line.getOptionValue("t"));
                dataToSend.add("temparatur ") ;
                //this.cliParameters.setTemparatur(line.getOptionValue("t"));
            }
            if (line.hasOption("l")){
                //map.put("luftfeuchtigkeit",line.getOptionValue("l"));
                dataToSend.add("luftfeuchtigkeit "); 
                //this.cliParameters.setLuftfeuchtigkeit(line.getOptionValue("l"));
            }
            if (line.hasOption("w")){
                //map.put("windgeschwindigkeit",line.getOptionValue("w"));
                dataToSend.add ("windgeschwindigkeit ");
                //this.cliParameters.setWindGeschwindigkeit(line.getOptionValue("w"));
            }
            if (line.hasOption("r")){
                //map.put("regen",line.getOptionValue("r"));
                dataToSend.add( "regen ");
                //this.cliParameters.setRegen(line.getOptionValue("R"));
            }
            
            if (dataToSend.isEmpty() | dataToSend.size() < 1) {
                LOGGER.info("No message given; using the default message.");
            } else {
                this.cliParameters.setMessage(dataToSend);
            }
            // Get whatever ist left, after the options have been processed.
            /*
            if (line.getArgList() == null || line.getArgList().isEmpty()) {
                LOGGER.info("No message given; using the default message.");
            } else {
                this.cliParameters.setMessage(line.getOptions().toString());
            }
            */
        } catch (MissingOptionException | MissingArgumentException | NumberFormatException e) {
            LOGGER.error("ERROR: " + e.getMessage() + "\n");
            //printHelp(options);
            System.exit(Defaults.EXIT_CODE_ERROR);
        } catch (ParseException e) {
            // Oops, something went totally wrong.
            LOGGER.error("ERROR: Parsing failed. Reason: " + e.getMessage());
        }

    }
    /////////////
    private Options createCliOptions() {
    	 // A helper option.
        Option help = Option.builder("h")
                .longOpt("help")
                .desc("Give this help list.")
                .build();
        // The broker address option.
        Option broker = Option.builder("b")
                .longOpt("broker")
                .desc("The broker address.")
                .hasArg()
                .argName("ADDRESS")
                .build();
        // The broker address option.
        Option port = Option.builder("p")
                .longOpt("port")
                .desc("The broker port.")
                .hasArg()
                .argName("PORT")
                .build();
        // The broker address option.
        Option protocol = Option.builder("P")
                .longOpt("protocol")
                .desc("The broker protocol.")
                .hasArg()
                .argName("PROTO")
                .build();
        // The topic option.
        Option topic = Option.builder("to")
                .longOpt("topic")
                .desc("The topic to listen to.")
                .hasArg()
                .argName("TOPIC")
                .build();
        
        //The temparatur option
        Option temparatur = Option.builder("t")
                .longOpt("temparatur")
                .desc("The TEMPARATUR is")
                .hasArg()
                .argName("TEMPARATUR")
                .build();
       
        //The luftfuechtigkeit option
        Option luftfeuchtigkeit = Option.builder("l")
        .longOpt("luftfeuchtigkeit")
        .desc("The LUFTFEUCHTIGKEIT is")
        .hasArg()
        .argName("LUFTFEUCHTIGKEIT")
        .build();
        
        //The windgeschwindigkeit option
        Option windgeschwindigkeit = Option.builder("w")
        .longOpt("windgeschwindigkeit")
        .desc("The WINDGESCHWINDIGKEIT is")
        .hasArg()
        .argName("WINDGESCHWINDIGKEIT")
        .build();
        
        //The regen option
        Option regen = Option.builder("r")
        .longOpt("regen")
        .desc("The REGEN is")
        .hasArg()
        .argName("REGEN")
        .build();

        // Create and add options.
        Options options = new Options();
        options.addOption(help);
        options.addOption(port);
        options.addOption(broker);
        options.addOption(protocol);
        options.addOption(topic);
        options.addOption(temparatur);
        options.addOption(luftfeuchtigkeit);
        options.addOption(windgeschwindigkeit);
        options.addOption(regen);

        // Return options.
        return options;
    }
    /**
     * Prints the help of the command.
     *
     * @param options The command's options.
     */
    private void printHelp(Options options) {
        // A help formatter.
        HelpFormatter formatter = new HelpFormatter();
        // Print help.
        formatter.printHelp(HELP_MSG, options);
    }
    /**
     * A private constructor to avoid
     * instantiation.
     */
    private CliProcessor() {
        this.cliParameters = CliParameters.getInstance();
    }
}
