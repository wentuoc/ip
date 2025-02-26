package camel.ui;

import java.util.Scanner;

import camel.exception.*;
import camel.file.FileHandling;
import camel.messages.*;
import camel.commands.*;

public class Camel {
    private static boolean parseInput(String input) throws CamelException {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "todo":
            try {
                Commands.addTask(words[0], words[1]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CamelException(ErrorMessages.TODO_FORMAT);
            }
        case "deadline":
            //fallthrough
        case "event":
            int firstSpaceIndex = input.indexOf(' ');
            String item = input.substring(firstSpaceIndex + 1);
            Commands.addTask(words[0], item);
            break;
        case "list":
            Commands.printList();
            break;
        case "mark":
            try {
                Commands.markTask(words[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new CamelException(ErrorMessages.MARK_FORMAT);
            }
            break;
        case "unmark":
            try {
                Commands.unmarkTask(words[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new CamelException(ErrorMessages.UNMARK_FORMAT);
            }
            break;
        case "help":
            NormalMessages.printHelp();
            break;
        case "bye":
            NormalMessages.printGoodbye();
            return true;
        default:
            throw new CamelException(ErrorMessages.UNKNOWN_COMMAND);
        }
        return false;
    }

    public static void main(String[] args) {
        FileHandling.openFile();
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasEnded = false;

        NormalMessages.printHello();

        while (!hasEnded) {
            input = in.nextLine();
            try {
                hasEnded = parseInput(input);
            } catch (CamelException e) {
                System.out.println(e.getMessage());
            }
        }
        FileHandling.saveFile();
    }
}
