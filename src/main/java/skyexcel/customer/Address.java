package skyexcel.customer;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {
    private String[] Servers = {
            "survivalgame.n-e.kr",
            "naver.com"
    };

    public boolean Equal(int index, String address) {
        try {

            InetAddress[] Server = InetAddress.getAllByName(Servers[index]);

            InetAddress[] TargetServer = InetAddress.getAllByName(address);

            if (Server != null) {
                for (InetAddress imsi : Server) {
                    for(InetAddress tsmi : TargetServer){
                        if(imsi.getHostAddress().equals(tsmi.getHostAddress())){
                            return true;
                        } else{
                            return false;
                        }
                    }
                    System.out.println(" 당신 도메인의 아이피: " + imsi.getHostAddress());
                }
                return true;
            } else {
                return false;
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
