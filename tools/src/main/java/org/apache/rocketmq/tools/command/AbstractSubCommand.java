package org.apache.rocketmq.tools.command;

import org.apache.rocketmq.remoting.RPCHook;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;

public abstract class AbstractSubCommand implements SubCommand {
    public DefaultMQAdminExt defaultMQAdminExt;

    protected DefaultMQAdminExt createMQAdminExt(RPCHook rpcHook) throws SubCommandException {
        if (this.defaultMQAdminExt != null) {
            return defaultMQAdminExt;
        } else {
            defaultMQAdminExt = new DefaultMQAdminExt(rpcHook);
            defaultMQAdminExt.setInstanceName(Long.toString(System.currentTimeMillis()));
            try {
                defaultMQAdminExt.start();
            } catch (Exception e) {
                throw new SubCommandException(this.getClass().getSimpleName() + " command failed", e);
            }
            return defaultMQAdminExt;
        }
    }
}
