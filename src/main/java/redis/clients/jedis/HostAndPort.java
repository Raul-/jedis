package redis.clients.jedis;

public class HostAndPort {
  public static final String LOCALHOST_STR = "localhost";

  private String host;
  private int port;

  public HostAndPort(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof HostAndPort) {
      HostAndPort hp = (HostAndPort) obj;

      String thisHost = convertHost(host);
      String hpHost = convertHost(hp.host);
      return port == hp.port && thisHost.equals(hpHost);

    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31 * convertHost(host).hashCode() + port;
  }

  @Override
  public String toString() {
    return host + ":" + port;
  }

  private String convertHost(String host) {
    if (host != null) {
      final String h = host.toLowerCase();

      // IPV4
      if (h.equals("127.0.0.1") || h.startsWith("localhost") || h.equals("0.0.0.0")) {
        return LOCALHOST_STR;
      }

      // IPV6
      else if (h.startsWith("::1") || h.startsWith("0:0:0:0:0:0:0:1")) {
        return LOCALHOST_STR;
      }
    }
    return host;
  }
}
