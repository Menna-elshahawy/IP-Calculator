package com.example;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class IPCalculator {

    public static String calculateIPData(String address, String netmask, String subsupernet) {
    	
    	StringBuilder resultBuilder = new StringBuilder();
    	
    	//Passing the address data
        resultBuilder.append("Address: ").append(address).append("-->").
        append(IPToBinary(address)).
        append("<br>");
        
        //Passing the Netmask data
        resultBuilder.append("Netmask: ").
        append(CIDRToSubnetMapping(Long.parseLong(netmask))).
        append(" = ").append(netmask).append("-->").
        append(IPToBinary(CIDRToSubnetMapping(Integer.parseInt(netmask)))).
        append("<br>");
        
        //Passing the Network data 
        String network = getNetworkFromIP(address,netmask);
        resultBuilder.append("Network: ")
        .append(network).append("/").append(netmask).append("-->").
        append(IPToBinary(network)).
        append("<br>");
        
        //Passing the Broadcast data
        String broadcast = getBroadcastFromIP(network, netmask);
        resultBuilder.append("Broadcast: ")
        .append(broadcast).append("-->").
        append(IPToBinary(broadcast)).
        append("<br>");
        
      //Passing the HostMin data
        String hostMin = getHostMin(network, netmask);
        resultBuilder.append("HostMin: ")
        .append(hostMin).append("-->").
        append(IPToBinary(hostMin)).
        append("<br>");
        
        //Passing the HostMax data
        String hostMax = getHostMax(network, netmask);
        resultBuilder.append("HostMax: ")
        .append(hostMax).append("-->").
        append(IPToBinary(hostMin)).
        append("<br>");

        //Passing the HostsCount data
        long hostsCount = getHostsCount(network, netmask);
        resultBuilder.append("HostsCount: ")
        .append(hostsCount).
        append("<br>");


        
        return resultBuilder.toString();
    }
    
    
    public static String CIDRToSubnetMapping(long cidr) {

    	long mask = (cidr == 0) ? 0 : (~((1 << (32 - cidr)) - 1));
    	long octet1 = (mask >> 24) & 255;
    	long octet2 = (mask >> 16) & 255;
    	long octet3 = (mask >> 8) & 255;
    	long octet4 = mask & 255;
        
        return String.format("%d.%d.%d.%d", octet1, octet2, octet3, octet4);
    }
    
    public static String IPToBinary(String IP) {
    	String [] IPParts = IP.split("\\.");
    	StringBuilder BinaryRep = new StringBuilder();
    	for(int i =0 ; i<IPParts.length;i++ ) {
    		long num  = Long.parseLong(IPParts[i]);
    		BinaryRep.append(String.format("%8s", Long.toBinaryString(num)).replace(' ', '0'));
    	}
    	return BinaryRep.toString();
    }
    
    public static String getNetworkFromIP(String IP, String mask) {
    	String IP1 = IPToBinary(IP);
    	String mask1 = IPToBinary(CIDRToSubnetMapping(Integer.parseInt(mask)));
    	Long sum =  Long.parseLong(IP1, 2)& Long.parseLong(mask1, 2);	
    	return intToIP(sum); 
    }
    
    
    public static String getBroadcastFromIP(String IP, String mask) {
    	long ipint =  ipToInt(IP);
    	long maskint = ipToInt(CIDRToSubnetMapping(Integer.parseInt(mask)));
//System.out.println(ipint);
//System.out.println(maskint);
System.out.println(intToIP(ipint&maskint));
    	long networkAddress = ipint & maskint;
        long inverseSubnetMask = ~maskint & 255;  
        long broadcastAddress = networkAddress | inverseSubnetMask;
    	return intToIP(broadcastAddress); 
    		
    }
    
    
    public static String getHostMin(String IP, String mask) {
    	return intToIP(ipToInt(getNetworkFromIP(IP, mask))+1);	
    }
    
    public static String getHostMax(String IP, String mask) {
    	return intToIP(ipToInt(getBroadcastFromIP(IP, mask))-1);	
    }
    
    public static String intToIP(long longValue) {
        // Extract each octet using bitwise operations
    	long intValue = (longValue & 0xFFFFFFFFL);
    	long octet1 = (intValue >> 24) & 0xFF;
    	long octet2 = (intValue >> 16) & 0xFF;
    	long octet3 = (intValue >> 8) & 0xFF;
    	long octet4 = intValue & 0xFF;
        // Format as IP address
        return String.format("%d.%d.%d.%d", octet1, octet2, octet3, octet4);
    }
    
    public static long ipToInt(String ip) {
        String[] octets = ip.split("\\.");
        long result = 0;
        for (int i = 0; i < octets.length; i++) {
            long octet = Long.parseLong(octets[i]);
            result += octet * Math.pow(256, octets.length-i-1);
        }
        return result;
    }
    
    public static long getHostsCount(String IP, String mask) {
    	return (ipToInt(getHostMax(IP,mask)) 
    			- ipToInt(getHostMin(IP,mask))+1);
    }
    
    
    public static void main(String[] args) {
    	//System.out.println(CIDRToSubnetMapping(18));
		//System.out.println(IPToBinary("192.168.0.1"));
    	//System.out.println(getNetworkFromIP("192.168.0.1","24"));
    	//System.out.println(ipToInt("192.168.1.1"));
    	System.out.println(getBroadcastFromIP("192.168.0.0","1"));
    	//System.out.println(getHostMin("192.168.0.1","24"));
//    	System.out.println(getHostMax("192.168.0.1","29"));
//    	System.out.println(ipToInt(getHostMax("192.168.0.1","29")) - ipToInt(getHostMin("192.168.0.1","29"))+1);
//    	System.out.println(getHostsCount("192.168.0.1","29"));
	}
}
