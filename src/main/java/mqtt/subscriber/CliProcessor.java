package mqtt.subscriber;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CliProcessor {
	 /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CliProcessor.class);

    /** The help message. */
    private static final String HELP_MSG = "socket-server.sh [OPTIONS]";

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
    public void parseCliOptions(String[] args) {
        // Command line options.
        Options options = createCliOptions();
        // Command line parser.
        CommandLineParser parser = new DefaultParser();

        try {
            // Parse the command line arguments.
            CommandLine line = parser.parse(options, args);
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
            if (line.hasOption("h")) {
                printHelp(options);
                System.exit(Defaults.EXIT_CODE_SUCCESS);
            }

        } catch (MissingOptionException | MissingArgumentException | NumberFormatException e) {
            LOGGER.error("ERROR: " + e.getMessage() + "\n");
            printHelp(options);
            System.exit(Defaults.EXIT_CODE_ERROR);
        } catch (ParseException e) {
            // Oops, something went totally wrong.
            LOGGER.error("ERROR: Parsing failed. Reason: " + e.getMessage());
        }
    }
	private void printHelp(Options options) {
		// A help formatter.
        HelpFormatter formatter = new HelpFormatter();
        // Print help.
        formatter.printHelp(HELP_MSG, options);
		
	}
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
        Option topic = Option.builder("t")
                .longOpt("topic")
                .desc("The topic to listen to.")
                .hasArg()
                .argName("TOPIC")
                .build();

        // Create and add options.
        Options options = new Options();
        options.addOption(help);
        options.addOption(broker);
        options.addOption(port);
        options.addOption(protocol);
        options.addOption(topic);
        // Return options.
        return options;
	}
	 /**
     * A private constructor to avoid
     * instantiation.
     */
    private CliProcessor() {
        this.cliParameters = CliParameters.getInstance();
    }
}
