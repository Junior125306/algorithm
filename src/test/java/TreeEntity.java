//工具类
//        多级菜单数据：id、父级id、用于装载子数据的集合

import java.util.ArrayList;
import java.util.List;

/**
 * 树形数据实体接口
 * @param <E>
 * @author jianda
 * @date 2017年5月26日
 */
public interface TreeEntity<E> {
    public String getId();
    public String getParentId();
    public void setChildList(List<E> childList);
}
/**
 * 解析树形数据工具类
 *
 * @author jianda
 * @date 2017年5月29日
 */
class TreeParser{
    /**
     * 解析树形数据
     * @param topId
     * @param entityList
     * @return
     * @author jianda
     * @date 2017年5月29日
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList=new ArrayList<>();

//获取顶层元素集合
        String parentId;
        for (E entity : entityList) {
            parentId=entity.getParentId();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }

//获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(),entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     * @param id
     * @param entityList
     * @return
     * @author jianda
     * @date 2017年5月29日
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(String id, List<E> entityList) {
        List<E> childList=new ArrayList<>();
        String parentId;

//子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }

//子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

//递归退出条件
        if(childList.size()==0){
            return null;
        }

        return childList;
    }
}
//测试
public class Menu implements TreeEntity<Menu>{
    public String id;
    public String name;
    public String parentId;
    public List<Menu> childList;
    //set、get方法...
    public class Test {
        public static void main(String[] args) {
            List<Menu> list=new ArrayList<>();
            Menu menu1=new Menu();
            menu1.setId("1");
            menu1.setParentId("0");
            menu1.setName("菜单1");
            list.add(menu1);

            Menu menu2=new Menu();
            menu2.setId("2");
            menu2.setParentId("0");
            menu2.setName("菜单2");
            list.add(menu2);

            Menu menu3=new Menu();
            menu3.setId("3");
            menu3.setParentId("1");
            menu3.setName("菜单11");
            list.add(menu3);

            Menu menu4=new Menu();
            menu4.setId("4");
            menu4.setParentId("3");
            menu4.setName("菜单111");
            list.add(menu4);

            List<Menu> menus=TreeParser.getTreeList("0",list);
            System.out.println(menus);
        }
    }