package org.yframework.toolkit.task;

import java.util.ArrayList;

public enum TaskManager
{
    INSTANCE;
    private ArrayList<Runnable> mRunables = new ArrayList<Runnable>();

    /**
     * run in background thread
     *
     * @param runnable
     */
    public void run(Runnable runnable)
    {
        mRunables.add(runnable);
        if (!TaskProxy.defaultExecutor.isBusy())
        {
            TaskProxy.defaultExecutor.execute(runnable);
        }
        else
        {
            new Thread(runnable).start();
        }
    }

    public void remove(Runnable runnable)
    {
        TaskProxy.defaultExecutor.getThreadPoolExecutor().remove(runnable);
    }

    public void shutdown()
    {
        TaskProxy.defaultExecutor.getThreadPoolExecutor().shutdown();
    }
}
