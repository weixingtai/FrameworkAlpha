package debug;

import com.alpha.module_common.base.BaseApplication;
import com.alpha.module_common.manager.FragmentPageManager;
import com.alpha.module_message.MessageFragment;

public class MessageApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FragmentPageManager.getInstance().addFragment(0, MessageFragment.getInstance());

    }
}
