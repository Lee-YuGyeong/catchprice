package com.market.catchprice.Contract;

import com.market.catchprice.Model.AutionResponse;

import java.util.List;

public interface AutionAdapterContract {

    // Adapter UI 이벤트를 위한 interface
    interface View {
        void notifyAdapter();   // UI Update
    }

    // Adapter 데이터 관리를 위한 Interface
    interface Model {
        void setData(List<AutionResponse> list);     // Adapter 데이터 갱신 메서드

        AutionResponse AutionResponse(int position);   // 클릭한 user의 정보를 반환하는 메서드
    }

}
