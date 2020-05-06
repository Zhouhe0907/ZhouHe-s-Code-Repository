import java.util.Scanner;

public class MySQLDemo {

    public static void main(String[] args) {
        MySQLTools.createTable();
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("英雄信息数据库");
        while (!quit)
        {
            int select;
            System.out.print(
                    "+---------------------------------------------------------+\n" +
                    "1.增加英雄  2.删除英雄 3.查询全部英雄信息  4.修改英雄信息  5.退出\n" +
                    "+---------------------------------------------------------+\n" +
                    "请选择服务："
            );
            select = scanner.nextInt();
            switch (select)
            {
                case 1: MySQLTools.insertIntoDataBank();break;
                case 2: MySQLTools.deleteFromDataBank();break;
                case 3: MySQLTools.referFromDataBank();break;
                case 4: MySQLTools.updateInDataBank();break;
                case 5: quit = true;break;
            }
        }
        System.out.println("程序运行完毕");

    }
}