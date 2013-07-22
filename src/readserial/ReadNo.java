/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readserial;

import java.awt.Window;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Bayu
 */
public class ReadNo {

    static SerialPort serialPort;
    private String nokartu;
    static JLabel label = new JLabel();

    public ReadNo() {
        serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new ReadNo.SerialPortReader());//Add SerialPortEventListener

        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
        label.setText("Letakan Kartu");
        Object[] options = {"Cancel"};
        int n = JOptionPane.showOptionDialog(null, label, "Title", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (n == 0) {
            try {
                serialPort.closePort();
            } catch (SerialPortException e) {
                System.out.println(e);
            }
        }
    }

    public String getNokartu() {
        return nokartu;
    }

    public void setNokartu(String nokartu) {
        System.out.println("Set : " + nokartu);
        this.nokartu = nokartu;
    }

    class SerialPortReader implements SerialPortEventListener {

        int counter = 0;
        String nomor = "";

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {//If data is available
                try {
                    int buffer[] = serialPort.readIntArray();
                    //System.out.print(buffer + " ");
                    if (buffer != null) {
                        for (int i = 0; i < buffer.length; i++) {
                            if (buffer[i] > 0) {
                                System.out.println(buffer[i]);
                                int data = buffer[i];
                                counter = counter + 1;
                                if (counter == 1) {
                                    if (data == 2) {
                                    } else {
                                        counter = 0;
                                        nomor = "";
                                    }
                                } else {
                                    if (counter >= 4 && counter <= 11) {
                                        char a = (char) data;
                                        nomor = nomor + a;
                                    } else {
                                        if (counter == 16) {
                                            if (data == 3) {
                                                //System.out.println(nomor);
                                                //String a = nomor;
                                                setNokartu(nomor);
                                                serialPort.closePort();
                                                Window w = SwingUtilities.getWindowAncestor(label);
                                                w.setVisible(false);
                                                counter = 0;
                                                nomor = "";
                                            } else {
                                                counter = 0;
                                                nomor = "";
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }

                } catch (SerialPortException ex) {
                    System.out.println(ex);

                }
            } else if (event.isCTS()) {//If CTS line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("CTS - ON");
                } else {
                    System.out.println("CTS - OFF");
                }
            } else if (event.isDSR()) {///If DSR line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("DSR - ON");
                } else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }
}
