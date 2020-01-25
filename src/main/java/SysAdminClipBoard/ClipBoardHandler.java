package SysAdminClipBoard;

import SysAdminClipBoard.views.PrimaryScene;

import java.awt.*;
import java.awt.datatransfer.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
inspired by code:
    https://coderanch.com/t/377833/java/listen-clipboard

 */

public  final class ClipBoardHandler extends ClipBoardHandlerRunner implements ClipboardOwner  {
    private static ClipBoardHandler cbh;
    private static Clipboard SYSTEM_CLIPBOARD;
    private Pattern pattern;
    private Matcher matcher;
    private static String text="";
    private static StringBuilder stringBuilder  = new StringBuilder("");

    private ClipBoardHandler() {

        SYSTEM_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = SYSTEM_CLIPBOARD.getContents(this);
        regainOwnership(trans);

        String myStringPattern = "(\r\n|\n)";
        pattern = Pattern.compile(myStringPattern);

    }

    public static ClipBoardHandler initilize(){
        if (cbh == null)
            cbh = new ClipBoardHandler();
        return cbh;
    }

    public void lostOwnership(Clipboard c, Transferable t) {
        int medthodCounter = 0;

        if (medthodCounter == 5) {
            System.out.println("Problem occured; workaround does not work");
            return;
        }

        try {
            Thread.sleep(20);
        } catch(Exception e) {
            System.out.println("Exception: " + e);
        }
        try {
            Transferable contents = SYSTEM_CLIPBOARD.getContents(this);
            processContents(contents);
            regainOwnership(contents);
        } catch (IllegalStateException ie){
            ie.printStackTrace();
            System.out.println("Rerunning method");
            medthodCounter++;
            lostOwnership(c,t);
        }
    }

    void processContents(Transferable t)  {

        try {
            text = (String)t.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Processing: " + t.toString() + " " + text);
        System.out.println("PrimaryScene.isIsSavePasteEnabled(): "+ PrimaryScene.getIsSavePasteEnabled());

        if (PrimaryScene.getIsSavePasteEnabled()){
            text = "#"+text;
        }

        if (PrimaryScene.getIsIsAppendPasteEnabled()){
            stringBuilder.append(text);
            stringBuilder.append("\n");
        }else if (stringBuilder.length() != 0)
            stringBuilder = new StringBuilder();

        matcher = pattern.matcher(text);
        if (matcher.find())
            PrimaryScene.AddNewEntryInVbox(cbh,"¶",text);
        else
            PrimaryScene.AddNewEntryInVbox(cbh," ",text);
    }

    void regainOwnership(Transferable t) {
        StringSelection selection;

        if (PrimaryScene.getIsSavePasteEnabled()){
            selection = new StringSelection(text);
            SYSTEM_CLIPBOARD.setContents(selection, this);

        }else if (PrimaryScene.getIsIsAppendPasteEnabled()){
            selection = new StringSelection(stringBuilder.toString());
            SYSTEM_CLIPBOARD.setContents(selection, this);
        }
        else
            SYSTEM_CLIPBOARD.setContents(t, this);
    }

    public void setPaste(String s){
        System.out.println("setting new entry in clipboard: " + s);
        StringSelection selection = new StringSelection(s);
        System.out.println(selection);
        SYSTEM_CLIPBOARD.setContents(selection, null);
    }
}
