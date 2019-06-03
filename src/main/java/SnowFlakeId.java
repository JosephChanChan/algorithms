import kits.GetNetworkAddress;

import java.math.BigInteger;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 雪花算法，总长 64 位，1位保留位 + 41位时间戳 + 10位机器码 + 12位序列号。
 *
 * @author Joseph
 * @date_time 2019/5/31 14:30
 */
public class SnowFlakeId {

    private volatile static int sequence = 1;
    private volatile static long lastTimeStamp ;
    private static long startUpTimeStamp ;

    private final static int machineOffset = 10;
    private final static int sequenceOffset = 12;
    private final static int timeStampOffset = machineOffset + sequenceOffset;

    // ~(-1 << machineOffset) 等价于 -1 ^ (-1 << machineOffset)
    private final static int machineMask = ~(-1 << machineOffset);
    /*private final static int sequenceMask = ~(-1 << sequenceOffset);
    private final static int timeStampMask = ~(-1 << timeStampOffset);*/

    private final static ReentrantLock reentrantLock = new ReentrantLock();

    static {
        startUpTimeStamp = System.currentTimeMillis();
        lastTimeStamp = startUpTimeStamp;
    }

    private SnowFlakeId() {
    }

    public static long nextSnowFlakeId(long machineNum) {
        long snow_flake ;

        // 只取低10位数值，再左移10位，方便下面或运算拼接
        machineNum = machineNum & machineMask << machineOffset;

        try {
            reentrantLock.lock();

            long currentTimeStamp = System.currentTimeMillis();
            // 如果当前是同一毫秒内并发请求
            if (currentTimeStamp == lastTimeStamp) {
                // 超过12位长度的序列号，等待下一毫秒
                int temp = ++sequence;
                if ((temp >> sequenceOffset) > 0) {
                    sequence = 1;
                    currentTimeStamp = wait2NextMillis();
                }
            }
            // 新的一毫秒，序列号从 1 开始
            else {
                sequence = 1;
            }

            snow_flake = (currentTimeStamp - startUpTimeStamp) << timeStampOffset | machineNum | sequence;
            lastTimeStamp = currentTimeStamp;
        }
        finally {
            reentrantLock.unlock();
        }
        return snow_flake;
    }

    private static long wait2NextMillis() {
        long currentTime = System.currentTimeMillis();
        while (currentTime <= lastTimeStamp) {
            currentTime = System.currentTimeMillis();
        }
        return currentTime;
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        long num = "abcdefg".hashCode();
        System.out.println(num);
        String binaryString = Long.toBinaryString(num);
        System.out.println(binaryString);
        num = num & machineMask;
        String binaryString1 = Long.toBinaryString(num);
        System.out.println(binaryString1);
        num = num << machineOffset;
        String binaryString2 = Long.toBinaryString(num);
        System.out.println(binaryString2);
        String mac = GetNetworkAddress.getAddress("mac");
        System.out.println(mac + " and it's hashcode is " + mac.hashCode());
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            long snowFlakeId = nextSnowFlakeId(mac.hashCode());
            boolean add = ids.add(snowFlakeId);
            if (!add) {
                System.out.println("Wrong! snowFlakeId already exists!");
                break;
            }
            System.out.println(snowFlakeId);
        }
    }

    /*private static long getMachineNum() throws SocketException, UnknownHostException {
        long machineNum ;
        String mac = kits.GetNetworkAddress.getAddress("mac");
        if (null == mac || "".equals(mac)) {
            machineNum = 0;
        }
        else {
            machineNum = mac.hashCode();
        }
        return machineNum;
    }*/
}
