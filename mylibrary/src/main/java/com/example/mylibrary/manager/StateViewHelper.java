package com.example.mylibrary.manager;



import com.example.mylibrary.state.IState;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.app.Context;



/**
 * StateView控制器，控制StateView显示、隐藏、创建
 */
public class StateViewHelper {

    /**
     * Show view
     * If stateView is null, you need to create
     *
     * @return
     */
    public static boolean showStater(Context context, ComponentContainer overallView, IState stater) {

        if (stater == null || overallView ==null) {
            return false;
        }
        Component staterView = stater.getView();
        if (staterView == null) {
            stater.onStateCreate(context, overallView);
            staterView = stater.getView();
            if (staterView == null) {
                return false;
            }
        }

        ComponentContainer.LayoutConfig layoutParams = overallView.getLayoutConfig();
        if (layoutParams == null) {
            layoutParams = new ComponentContainer.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT);
        }
        layoutParams.width =ComponentContainer.LayoutConfig.MATCH_PARENT;
        layoutParams.height = ComponentContainer.LayoutConfig.MATCH_PARENT;
        if (overallView.getChildIndex(staterView) < 0 && staterView.getComponentParent() == null) {
            overallView.addComponent(staterView, layoutParams);
        }
        stater.getView().setVisibility(Component.VISIBLE);
        stater.onStateResume();
        return true;
    }

    /**
     *Hide view
     *If the stateView is not created, no processing is done
     *
     * @return
     */
    public static boolean hideStater(IState stater) {

        if (stater == null || stater.getView() == null) {
            return false;
        }
        stater.getView().setVisibility(Component.HIDE);
        stater.onStatePause();
        return true;
    }

}
