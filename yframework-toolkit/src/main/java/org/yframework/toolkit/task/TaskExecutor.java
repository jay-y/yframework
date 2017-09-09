package org.yframework.toolkit.task;

import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: TaskExecutor <br>
 * Description: 支持优先级的线程池管理类. <br>
 * Date: 2015-12-2 下午3:41:42 <br>
 *
 * @author ysj
 * @since JDK 1.7
 */
public class TaskExecutor implements Executor
{
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2; // 核心线程数
    private static final int MAXIMUM_POOL_SIZE = 256; // 最大线程数
    private static final int KEEP_ALIVE = 1; // 线程池维护线程所允许的空闲时间

    private final ThreadPoolExecutor executor;
    private final ThreadFactory factory;

    public TaskExecutor()
    {
        this(CORE_POOL_SIZE);
    }

    public TaskExecutor(int poolSize)
    {
        Comparator<Runnable> sRunnableComparator = (lhs, rhs) ->
        {
            // 根据优先级排序
            if (lhs instanceof TaskRunnable && rhs instanceof TaskRunnable)
            {
                return ((TaskRunnable) lhs).priority.ordinal() - ((TaskRunnable) rhs).priority.ordinal();
            }
            else
            {
                return 0;
            }
        };
        BlockingQueue<Runnable> mPoolWorkQueue = new PriorityBlockingQueue<Runnable>(MAXIMUM_POOL_SIZE, sRunnableComparator);
        this.factory = new ThreadFactory()
        {
            private final AtomicInteger mCount = new AtomicInteger(1); // 安全计数

            @Override
            public Thread newThread(Runnable runnable)
            {
                return new Thread(runnable, "TaskExecutor #" + mCount.getAndIncrement());
            }
        };
        this.executor = new ThreadPoolExecutor(poolSize, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, // 线程池维护线程所允许的空闲时间的单位
            mPoolWorkQueue, // 线程池所使用的缓冲队列
            factory);
    }

    public ThreadPoolExecutor getPoolExecutor()
    {
        return executor;
    }

    public ThreadFactory getFactory()
    {
        return factory;
    }

    public int getPoolSize()
    {
        return this.executor.getCorePoolSize();
    }

    public void setPoolSize(int poolSize)
    {
        if (poolSize > 0)
        {
            this.executor.setCorePoolSize(poolSize);
        }
    }

    public ThreadPoolExecutor getThreadPoolExecutor()
    {
        return this.executor;
    }

    public boolean isBusy()
    {
        return this.executor.getActiveCount() >= this.executor.getCorePoolSize();
    }

    public void execute(final Runnable runnable)
    {
        this.executor.execute(runnable);
    }
}
