package com.nbfox.component_base.network.api;



public class NetApi {
    //-------------用户---------
    public static final String USER_LOGIN="super_service/api/user/login";
    public static final String USER_GET_CODE="super_service/api/user/getAuthCode";
    public static final String USER_FIND_PWD="super_service/api/user/resetPassword";
    public static final String USER_APP_VISION="super_service/wechat/appVersion/getAppVersion";

    //-------------首页----------
    public static final String HOME_MEUN="super_service/api/user/menuList";
    public static final String HOME_SIGN="super_service/api/user/checkIn";
    public static final String HOME_SIGN_OUT="super_service/api/user/checkOut";

    public static final String HOME_PUSHI_NOTICE="super_service/api/notice/indexTotal";
    public static final String HOME_PUSHI_PROJECT_FIX="super_service/api/repairBill/indexTotal";
    public static final String HOME_PUSHI_ENTETPRISE="super_service/api/emig/indexTotal";
    public static final String HOME_PUSHI_THING="super_service/api/release/indexTotal";

    public static final String HOME_BANNER="super_service/api/notice/bannerList";
    public static final String HOME_MENU="super_service/api/user/indexMenuList";
    public static final String HOME_CHILD_MENU="super_service/api/user/childrenMenuList";
    public static final String NOTICE_LIST="super_service/api/notice/noticeList";



    //-------------公告审核----------
    public static final String AFFICEHE_LIST="super_service/api/notice/getList";



    //-------------工程维修----------
    public static final String PROJECT_FIX_LIST="super_service/api/repairBill/list";
    public static final String PROJECT_FIX_MANAGE_LIST="super_service/api/repairBill/managerList";
    public static final String PROJECT_FIX_ADD_BY_TYPE="super_service/api/repairBill/index";
    public static final String PROJECT_FIX_CATORY="super_service/api/repairBill/getRepairTypeaDetail";

    public static final String PROJECT_FIX_SUBIMT="super_service/api/repairBill/repair";
    public static final String PROJECT_FIX_DETAILS="super_service/api/repairBill/getDetail";
    public static final String PROJECT_FIX_GRB_BILL="super_service/api/repairBill/grabBill";

    public static final String PROJECT_FIX_CANCEL_BILL="super_service/api/repairBill/cancelPendBill";
    public static final String PROJECT_FIX_REFUSE_RECIVE_BILL="super_service/api/repairBill/handleTransferBill";
    public static final String PROJECT_FIX_TRAN_LIST="super_service/api/repairBill/getRepairUser";

    public static final String PROJECT_FIX_TRAN_SUBIMT="super_service/api/repairBill/transferBill";
    public static final String PROJECT_FIX_PEND_LIST="super_service/api/repairBill/getPendReason";
    public static final String PROJECT_FIX_PEND_SUBIMT="super_service/api/repairBill/pendBill";

    public static final String PROJECT_FIX_PEND_SDEND_SUBIMT="super_service/api/repairBill/sendBill";
    public static final String PROJECT_FIX_SUPORT="super_service/api/repairBill/getSupportUser";
    public static final String PROJECT_FIX_SUPORT_SUBMIT="super_service/api/repairBill/addSupportUser";

    public static final String PROJECT_FIX_MATERIAL_ADD="super_service/api/repairBill/addMaterial";
    public static final String PROJECT_FIX_MATERIAL_DELETE="super_service/api/repairBill/deleteMaterial";
    public static final String PROJECT_FIX_MATERIAL_LIST="super_service/api/repairBill/getMaterial";

    public static final String PROJECT_FIX_REFUSE_ADUDIT_BILL="super_service/api/repairBill/auditBill";
    public static final String PROJECT_FIX_FINISH_BILL="super_service/api/repairBill/completeBill";
    public static final String PROJECT_FIX_FINISH_COST="super_service/api/repairBill/getWeiXinPayCode";

    public static final String PROJECT_FIX_FINISH_NO_COST="super_service/api/repairBill/noPay";
    public static final String PROJECT_FIX_FINISH_NO_COST_PAY="super_service/api/repairBill/hasPaid";

    //-------------物品放行----------

    public static final String THING_LIST="super_service/api/release/auditList";
    public static final String THING_DETAIL="super_service/api/release/getDetail";
    public static final String THING_AGREE_REFUSE="super_service/api/release/refuse";
    public static final String THING_SUBIMT_AGREE="super_service/api/release/agree";


    //-------------宿舍管理----------

    public static final String HOSTEL_MANAGER_LIST="super_service/api/dorm/getCheckOutList";
    public static final String HOSTEL_MANAGER_DETAIL="super_service/api/dorm/getDormRoomerInfo";
    public static final String HOSTEL_MANAGER_SUBMIT="super_service/api/dorm/auditDormCheckOut";


    //-------------企业入驻----------

    public static final String ENTERPRISE_LIST="super_service/api/enter/getEnterpriseList";
    public static final String ENTERPRISEH_HOSTORY_LIST="super_service/api/enter/getEnterList";
    public static final String ENTERPRISE_DETAIL="super_service/api/enter/getEnterpriseInfo";

    public static final String ENTERPRISEH_WOTER_ELEC="super_service/api/enter/getWaterList";
    public static final String ENTERPRISEH_SUBIMT_WOTER_ELEC="super_service/api/enter/submitWater";
    public static final String ENTERPRISEH_UPLOAD_PIC="super_service/api/enter/uploadImage";

    public static final String ENTERPRISE_UPDATE_IN="super_service/api/enter/updateEnterInfoo";
    public static final String ENTERPRISE_CUNSTIOM="super_service/api/enter/getCustomerService";
//    public static final String ENTERPRISEH_SUBMIT_REFUSE_AGREE="super_service/api/enter/setEnterReason";
    public static final String ENTERPRISEH_SUBMIT_REFUSE_AGREE="super_service/api/enter/optionEnterpriseOrder";

    public static final String ENTERPRISE_IS_IN="super_service/api/enter/isPowerButton";
    public static final String ENTERPRISEH_SERVICE="super_service/api/enter/getRepairTypeUser";
    public static final String ENTERPRISEH_WOTER_ELEC_LIST="super_service/api/enter/getWateSizeList";

    public static final String ENTERPRISEH_PEOPLE_CONFIRM="super_service/api/enter/updateEnterPeson";
    public static final String ENTERPRISEH_WOTER_FIX_LIST="super_service/api/enter/getRepairNumberList";
    public static final String ENTERPRISEH_FIX_CONFIRM="super_service/api/enter/updateEnterRpairCode";

    public static final String ENTERPRISEH_FIX_BUTTOPM="super_service/api/enter/isPowerButton";

    //-------------企业迁出----------
    public static final String ENTERPRISEH_OUT_APPLAY_SUBMIT="super_service/api/emig/saveEmigration";
    public static final String ENTERPRISEH_OUT_APPLAY_CAMPANY="super_service/api/emig/getFloorVirList";
    public static final String ENTERPRISEH_OUT_APPLAY_GET_CAMPANY_INFO="super_service/api/emig/getEmigInfoByVirId";

    public static final String ENTERPRISEH_OUT_APPLAY_REASON="super_service/api/emig/getEmigrationReason";
    public static final String ENTERPRISEH_OUT_APPLAY_TYPE="super_service/api/emig/getEmigrationType";
    public static final String ENTERPRISEH_OUT_APPLAY_HOUSE="super_service/api/emig/getBuildList";

    public static final String ENTERPRISEH_OUT_DETAILS="super_service/api/emig/getEmigInfo";
    public static final String ENTERPRISEH_OUT_MODFY_RECORD="super_service/api/emig/updateEmigByOtherRecord";
    public static final String ENTERPRISEH_OUT_MODFY_CACULATE="super_service/api/emig/updateEmigBySettle";
    public static final String ENTERPRISEH_OUT_GET_CACULATE="super_service/api/emig/getEmigEmigSettle";

    public static final String ENTERPRISEH_NAME="super_service/api/emig/getBuildVirList";
    public static final String APPLAY_CAMPANY = "super_service/api/air/selectCompany";





    //-------------会议室模块----------
    public static final String MEETTING_ROOM_TOKEN = "Login/LoginByWeChatAPPToken";
    public static final String MEETTING_ROOM_LAYER_SELECT = "api/Organization/GetUserBuildingFloor";
    public static final String MEETTING_ROOM_PLPACE_SELECET = "api/BasicMaintianAPI/GetResourceData";
    public static final String MEETTING_ROOM_LIST = "api/ReportAPI/QueryBookDataforResrAdm";

    public static final String MEETTING_ROOM_DETAIL = "api/BookAppAPI/GetResrBookAPP";
    public static final String MEETTING_ROOM_DETAIL_SIGN = "api/BookOperateWXAPI/SaveBookSign";
    public static final String MEETTING_ROOM_DETAIL_PAYCODE = "api/ResrBookDelayAPI/GetAPPPayBookDelayParams";

    public static final String MEETTING_ROOM_DETAIL_CACEL_ORDER_DELAY = "api/ResrBookDelayAPI/CancelResrBookDelayData";
    public static final String MEETTING_ROOM_DETAIL_CACEL_ORDER = "api/BookOperateAPI/CancelResrBookData";
    //    public static final String MEETTING_ROOM_DETAIL_APPLY_DELAY="api/ResrBookDelayAPI/CreateBookDelayDataSimple";
    public static final String MEETTING_ROOM_DETAIL_APPLY_DELAY = "/api/ResrBookDelayAPI/CreateBookDelayDataSpecial";

    public static final String MEETTING_ROOM_DETAIL_APPLY_DELAY_DATA = "api/BookAppAPI/BookCost";
    public static final String MEETTING_ROOM_EXTRA_LSIT = "/api/ServiceAPI/GetServiceInfoData";
    public static final String MEETTING_ROOM_DETAIL_ADD_TIME = "api/ResrBookDelayAPI/CheckDelayTime";

    public static final String MEETTING_ROOM_FINISH_NO_COST_PAY = "api/BookOperateWXAPI/CheckPayStatus";
    public static final String MEETTING_ROOM_FINISH_UPDATE_PAY_STATUS = "api/ResrBookDelayAPI/PayOverBookDelayData";


    //-----------洗车模块----------------
    public static final String CARG_WASHING_ORDER_LIST="super_service/api/washcar/getOrderList";
    public static final String CARG_WASHING_ORDER_DETIAL="super_service/api/washcar/getWashOrderInfo";
    public static final String CARG_WASHING_ORDER_CANCEL="super_service/api/washcar/cancelWashOrder";

    public static final String CARG_WASHING_ORDER_REASON="super_service/api/washcar/getSlipReason";
    public static final String CARG_WASHING_ORDER_TRANSFOR="super_service/api/washcar/transferBill";
    public static final String CARG_WASHING_ORDER_EMPLOY="super_service/api/washcar/getWashCarService";

    public static final String CARG_WASHING_ORDER_DISPATH="super_service/api/washcar/dispatch";
    public static final String CARG_WASHING_ORDER_FINISH="super_service/api/washcar/transferBill";
    public static final String CARG_WASHING_ORDER_DO="super_service/api/washcar/updateWashOrder";



    //-------------空调管理模块----------
    public static final String AIRE_CONDITIONER_LIST = "super_service/api/air/auditList";
    public static final String AIRE_CONDITIONER_DETAIL = "super_service/api/air/getDetail";
    public static final String AIRE_CONDITIONER_YES_OR_NO = "super_service/api/air/audit";

    public static final String AIRE_CONDITIONER_CANCEL_ORDER = "super_service/api/air/cancel";
    public static final String AIRE_CONDITIONER_SELECT_EMPLOYS = "super_service/api/air/getAirSwitchUser";
    public static final String AIRE_CONDITIONER_LAYER = "super_service/api/air/getFloorNosByUserId";

    public static final String APPLAY_CAMPANY_NUM_AREA = " super_service/api/air/getRoomsAndAreaByVirtualId";
    public static final String APPLAY_CAMPANY_AIRE = "super_service/api/air/apply";
    public static final String APPLAY_CAMPANY_AIRE_SUBMIT = "super_service/api/air/applyConfirm";




    //-------------我的----------

    public static final String USER_LOGOUT="super_service/api/user/loginOut";
    public static final String ME_UPDATE_PWD="super_service/api/user/modifyPassword";
    public static final String ME_UPLOAD_AVATAR="super_service/api/user/uploadPhoto";
    public static final String PUSH_TOEN="super_service/api/user/updateDeviceNo";


}
