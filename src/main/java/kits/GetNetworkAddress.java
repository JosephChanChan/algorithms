package kits;

import java.net.*;
import java.util.Enumeration;

/**
 * StackOverflow 搬运过来..
 * https://stackoverflow.com/questions/6164167/get-mac-address-on-local-machine-with-java
 */
public class GetNetworkAddress {

    /*public static void main(String[] args) {
        String mac = kits.GetNetworkAddress.getAddress("mac");
        System.out.println(mac);
    }*/

    public static String getAddress(String addressType) throws SocketException, UnknownHostException {
        String address ;
        InetAddress lanIp = null;

        String ipAddress ;
        Enumeration<NetworkInterface> net ;
        net = NetworkInterface.getNetworkInterfaces();

        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();

            byte[] mac ;
            while (
                    addresses.hasMoreElements() &&
                    null != (mac = element.getHardwareAddress()) &&
                    !isVMMac(mac)
                  )
            {
                InetAddress ip = addresses.nextElement();
                if (ip instanceof Inet4Address) {
                    if (ip.isSiteLocalAddress()) {
                        ipAddress = ip.getHostAddress();
                        lanIp = InetAddress.getByName(ipAddress);
                    }
                }
            }
        }

        if (lanIp == null) return null;

        if (addressType.equals("ip")) {
            address = lanIp.toString().replaceAll("^/+", "");
        }
        else if (addressType.equals("mac")) {
            address = getMacAddress(lanIp);
        }
        else {
            throw new RuntimeException("Specify \"ip\" or \"mac\"");
        }

        return address;
    }

    private static String getMacAddress(InetAddress ip) {
        String address = null;
        try {

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            address = sb.toString();
        }
        catch (SocketException ex) {
            ex.printStackTrace();
        }

        return address;
    }

    private static boolean isVMMac(byte[] mac) {
        if(null == mac) return false;
        byte invalidMacs[][] = {
                {0x00, 0x05, 0x69},             //VMWare
                {0x00, 0x1C, 0x14},             //VMWare
                {0x00, 0x0C, 0x29},             //VMWare
                {0x00, 0x50, 0x56},             //VMWare
                {0x08, 0x00, 0x27},             //Virtualbox
                {0x0A, 0x00, 0x27},             //Virtualbox
                {0x00, 0x03, (byte)0xFF},       //Virtual-PC
                {0x00, 0x15, 0x5D}              //Hyper-V
        };

        for (byte[] invalid: invalidMacs){
            if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2]) return true;
        }

        return false;
    }

}