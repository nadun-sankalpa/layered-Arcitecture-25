package lk.Ijse.pos.bo;

import lk.Ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.Ijse.pos.bo.custom.impl.ItemBoImpl;
import lk.Ijse.pos.bo.custom.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory =new BOFactory() : boFactory;
    }
    public enum BOType{
        CUSTOMER,ITEM,PURCHASE_ORDER
    }
    public SuperBO getBO(BOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBoImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }
}
