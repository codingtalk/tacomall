package store.tacomall.apima.vo.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.tacomall.common.bo.pay.PayResBo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleFormPayVo {
    Integer saleFormId;
    PayResBo payResBo;
}
