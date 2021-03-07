package sales.management.system.dtoRequest;

import lombok.Getter;

@Getter
public class NewCommodityDto {

    private int groupId;
    private int unitId;
    private boolean goods;
    private String name;
    private String description;
}
