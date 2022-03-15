
/**
 * 雪花算法，总长 64 位，最高位是0代表整个id为正数 + 42位时间戳 + 8位机器码 + 13位序列号
 * 42位时间长度可用139年
 * 8位机器码可部署256台实例
 * 13位序列号一毫秒内可生成8192个id
 * 可根据需要调整各部分位数长度，适合自己的业务
 */
public class SnowFlakeId {

    /**
     * 毫秒内序列号
     */
    private int sequence = 0;

    /**
     * 机器id
     */
    private final int workerId ;

    /**
     * 纪元从2022-01-01零点开始
     */
    private final long startUpTime = 1640966400000L;

    /**
     * 上一个id的时间戳
     */
    private long lastTime = startUpTime;

    /**
     * 机器位数
     */
    private final int machineBit = 8;

    /**
     * 序列位数
     */
    private final int sequenceBit = 13;

    /**
     * 机器码偏移量 12位
     */
    private final int machineOffset = sequenceBit;

    /**
     * 时间戳偏移量 18位
     */
    private final int timestampOffset = machineBit + sequenceBit;

    /**
     * 机器掩码 0000 0000 ... 111 111
     * ~(-1 << MACHINE_BIT) 等价于 -1 ^ (-1 << MACHINE_BIT)
     */
    private final int machineMask = ~(-1 << machineBit);

    /**
     * 0111 1111 ... 1111 64位，用来去最高位的数，使最高位保持为0
     */
    private final long topMask = Long.MAX_VALUE;



    public SnowFlakeId(int workerId) {
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long nowMillis = System.currentTimeMillis();
        if (nowMillis < lastTime) {
            // 时钟回拨
            nowMillis = handleTimeBack(nowMillis, lastTime);
        }
        if (nowMillis > lastTime) {
            // 新的一毫秒，序列号从 0 开始
            sequence = 0;
        }
        else {
            // 超过12位长度的序列号，等待下一毫秒
            if ((sequence >> sequenceBit) > 0) {
                sequence = 0;
                nowMillis = wait2NextMillis();
            }
        }
        sequence++;
        lastTime = nowMillis;
        return (((nowMillis - startUpTime) << timestampOffset) | ((workerId & machineMask) << machineOffset) | sequence) & topMask;
    }

    private long wait2NextMillis() {
        long currentTime = System.currentTimeMillis();
        while (currentTime <= lastTime) {
            currentTime = System.currentTimeMillis();
        }
        return currentTime;
    }

    private long handleTimeBack(long now, long last) {
        if (last - now > 5) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for diff %d milliseconds", last - now));
        }
        try {
            // 时钟回拨在5ms内，忍一忍
            wait((last - now) << 1);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        now = System.currentTimeMillis();
        if (now < last) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for diff %d milliseconds", last - now));
        }
        return now;
    }

}
