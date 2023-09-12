import java.net.InetAddress;

public class AdresseIPPort {
	private InetAddress adresseIP;
    private int port;

    public AdresseIPPort(InetAddress adresseIP, int port) {
        this.adresseIP = adresseIP;
        this.port = port;
    }

    public InetAddress getAdresseIP() {
        return adresseIP;
    }

    public int getPort() {
        return port;
    }
}
