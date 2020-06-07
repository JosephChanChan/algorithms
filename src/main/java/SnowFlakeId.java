
import kits.GetNetworkAddress;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * 雪花算法，总长 64 位，最高位是0代表整个id为正数 + 41位时间戳 + 10位机器码（可细分数据中心、机器码） + 12位序列号。
 *
 * @author Joseph
 * @since  2019/5/31 14:30
 */
public class SnowFlakeId {

    private static int SEQUENCE = 1;
    private static long LAST_TIMESTAMP ;
    private static final long START_UP_TIMESTAMP ;

    // 机器位数
    private static final int MACHINE_BIT = 10;
    // 序列位数
    private static final int SEQUENCE_BIT = 12;

    private static final int MACHINE_OFFSET = SEQUENCE_BIT;
    private static final int TIMESTAMP_OFFSET = MACHINE_BIT + SEQUENCE_BIT;

    // ~(-1 << MACHINE_BIT) 等价于 -1 ^ (-1 << MACHINE_BIT)
    private final static int MACHINE_MASK = ~(-1 << MACHINE_BIT);

    // TOP_ZERO = 0111 1111 ... 1111    用来去最高位的数，使最高位保持为0
    private static final int TOP_ZERO = Integer.MAX_VALUE;

    static {
        START_UP_TIMESTAMP = LocalDateTime.parse("2020-06-07 00:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LAST_TIMESTAMP = START_UP_TIMESTAMP;
    }

    private SnowFlakeId() {
    }

    public static long nextSnowFlakeId(long machineNum) {
        long snow_flake ;

        // 只取低10位数值
        machineNum = machineNum & MACHINE_MASK;

        synchronized (SnowFlakeId.class) {
            long currentTimeStamp = System.currentTimeMillis();
            // 如果当前是同一毫秒内并发请求
            if (currentTimeStamp == LAST_TIMESTAMP) {
                // 超过12位长度的序列号，等待下一毫秒
                if ((++SEQUENCE >> SEQUENCE_BIT) > 0) {
                    SEQUENCE = 1;
                    currentTimeStamp = wait2NextMillis();
                }
            }
            // 新的一毫秒，序列号从 1 开始
            else {
                SEQUENCE = 1;
            }
            snow_flake = (currentTimeStamp - START_UP_TIMESTAMP) << TIMESTAMP_OFFSET | machineNum << MACHINE_OFFSET | SEQUENCE & TOP_ZERO;
            LAST_TIMESTAMP = currentTimeStamp;
        }
        return snow_flake;
    }

    private static long wait2NextMillis() {
        long currentTime = System.currentTimeMillis();
        while (currentTime <= LAST_TIMESTAMP) {
            currentTime = System.currentTimeMillis();
        }
        return currentTime;
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        /*long l1 = DateKit.addYears(DateKit.now(), -2).getTime();
        long diff = System.currentTimeMillis() - l1;
        System.out.println(diff);
        System.out.println(patch(Long.toBinaryString(diff)));

        System.out.println(Long.toBinaryString(-1));

        System.out.println(diff << TIMESTAMP_OFFSET);
        System.out.println(Long.toBinaryString(diff << TIMESTAMP_OFFSET));*/

        String mac = GetNetworkAddress.getAddress("mac");
        System.out.println(mac + " and it's hashcode is " + mac.hashCode());
        int h = mac.hashCode(), k = 10000000, i ;
        Set<Long> ids = new HashSet<>(k);
        for (int j = 0; j < 10; j++) {
            for (i = 0; i < k; i++) {
                long snowFlakeId = nextSnowFlakeId(h);
                boolean nx = ids.add(snowFlakeId);
                if (!nx) {
                    System.out.println(i);
                    System.out.println("Wrong! snowFlakeId already exists!");
                    break;
                }
                /*System.out.println(snowFlakeId);*/
            }
            ids.clear();
        }
    }

}
