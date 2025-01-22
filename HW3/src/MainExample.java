public class MainExample {
    public static void main(String[] args) {
        System.out.println("Test 1 starts");
        test1();
        System.out.println("Test 1 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 2 starts");
        test2();
        System.out.println("Test 2 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 3 starts");
        test3();
        System.out.println("Test 3 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 4 starts");
        test4();
        System.out.println("Test 4 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 5 starts");
        test5();
        System.out.println("Test 5 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 6 starts");
        test6();
        System.out.println("Test 6 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 7 starts");
        test7();
        System.out.println("Test 7 done");
        System.out.println("--------------------------------------------");

        System.out.println("Test 8 starts");
        test8();
        System.out.println("Test 8 done");
        System.out.println("--------------------------------------------");


    }

    /**
     * Run first test of hw2
     */
    public static void test1(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");
        Room room5 = new Room("Room 5");
        Room room6 = new Room("Room 6");

        Room tempRoom = new Room("Room 5");

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);
        gameManager.addRoom(room3);
        gameManager.addRoom(room4);
        gameManager.addRoom(room5);
        gameManager.addRoom(room6);
        gameManager.removeRoom(tempRoom);
        gameManager.addRoom(room6);



        gameManager.connectRooms(room1, room2, Direction.NORTH);
        gameManager.connectRooms(room1, room2, Direction.NORTH);
        gameManager.connectRooms(room1, room3, Direction.NORTH);
        gameManager.connectRooms(room3, room2, Direction.NORTH);
        gameManager.connectRooms(room1, room3, Direction.SOUTH);

        gameManager.removeRoom(room5);
        gameManager.removeRoom(room2);
        gameManager.connectRooms(room1, room4, Direction.NORTH);
    }
    /**
     * Run second test of hw2
     */
    public static void test2(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");

        Item item1 = new Bag("Item 1", 5, 2);  // String name, int value, int size
        Item item2 = new Key("Item 2", 3);
        Item item3 = new Relic("Item 3", 9);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);

        gameManager.addItem(room1, item1);
        gameManager.addItem(room1, item2);
        gameManager.addItem(room1, item3);

    }
    /**
     * Run third test of hw2
     */
    public static void test3(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        gameManager.addRoom(room1);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);

        gameManager.startPlayer(room1);

        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Relic("Item 2", 6);
        Item item3 = new Relic("Item 3", 7);

        Item tempItem = new Relic("Item 2", 6);

        gameManager.addItem(room1, item1);
        gameManager.addItem(room1, item2);

        gameManager.dropItem(item1);
        gameManager.pickUpItem(item1);
        gameManager.pickUpItem(item1);
        gameManager.pickUpItem(tempItem);

        tempItem = new Relic("I don't know what is the name of the relic, please find it for me", 2);

        gameManager.dropItem(tempItem);
        gameManager.pickUpItem(item3);
        gameManager.pickUpItem(item1);

        gameManager.addItem(room1, item3);
        gameManager.pickUpItem(item3);
    }
    /**
     * Run fourth test of hw2
     */
    public static void test4(){
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");

        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Relic("Item 2", 6);
        Item item3 = new Relic("Item 3", 7);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);

        gameManager.addItem(room2, item1);
        gameManager.addItem(room2, item2);
        gameManager.addItem(room1, item3);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.connectRooms(room1, room2, Direction.NORTH);

        gameManager.movePlayer(Direction.SOUTH);
        gameManager.pickUpItem(item3);
        gameManager.activatePuzzle(room1);

        gameManager.movePlayer(Direction.NORTH);
        gameManager.solvePuzzle();
        gameManager.movePlayer(Direction.NORTH);

        gameManager.dropItem(item3);
        gameManager.solvePuzzle();
        gameManager.movePlayer(Direction.SOUTH);
        gameManager.dropItem(item3);
        gameManager.disassembleItem(item1);
        gameManager.disassembleItem(item3);
        gameManager.pickUpItem(item3);
    }

    public static void test5() {
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");

        Item item1 = new Relic("Item 1", 2);
        Item item2 = new Relic("Item 2", 6);

        gameManager.addItem(room1, item1);

        gameManager.addRoom(room1);

        gameManager.addItem(room1, item2);
        gameManager.addItem(room1, item1);

        Player player = new Player("Player 1", 2);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.pickUpItem(item1);

        gameManager.removePlayer(player);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.disassembleItem(item1);
        gameManager.removePlayer(player);

        gameManager.removeRoom(room1);
        gameManager.addRoom(room1);

        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);

        gameManager.disassembleItem(item2);
    }

    public static void test6() {
        GameManager gameManager = new GameManager();
        Player player = new Player("Player 1", 5);


        // initiating items in game
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");

        Item relic1 = new Relic("relic1", 1);
        Item relic2 = new Relic("relic2", 1);
        Item relic3 = new Relic("relic3", 1);
        Item relic4 = new Relic("relic4", 1);
        Item relic5 = new Relic("relic5", 1);

        Item largebag = new LargeBag("large bag", 5, 6);
        //Item regular = new Bag("regular bag", 5,5);

        gameManager.addRoom(room1);
        gameManager.addRoom(room2);
        gameManager.addRoom(room3);

        //relics in rooms
        gameManager.addItem(room1, relic1);
        gameManager.addItem(room1, relic2);
        gameManager.addItem(room2, relic3);
        gameManager.addItem(room2, relic4);
        gameManager.addItem(room3, relic5);
        gameManager.addItem(room3,largebag);

        // connecting the rooms
        gameManager.connectRooms(room1, room2, Direction.NORTH);
        gameManager.connectRooms(room2, room3, Direction.NORTH);

        // the GAME
        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);
        gameManager.pickUpItem(relic1);
        gameManager.pickUpItem(relic2);
        gameManager.movePlayer(Direction.NORTH);
        gameManager.pickUpItem(relic3);
        gameManager.pickUpItem(relic4);
        gameManager.movePlayer(Direction.NORTH);
        gameManager.pickUpItem(relic5);
        gameManager.useItem(largebag);

        System.out.println("GAME IS OVER");
    }

    public static void test7() {
        // בודק איסוף של תיקים רגילים לתוך תיק גדול ואז מחליף בין שני תיקים גדולים + הזנת גודל לא מתאים לתיק גדול
        GameManager gameManager = new GameManager();
        Player player = new Player("Player 1", 2);


        // initiating items in game
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");

        Item reg1 = new Bag("reg1", 1,1);
        Item reg2 = new Bag("reg2", 1,1);
        Item reg3 = new Bag("reg3", 1,1);
        Item reg4 = new Bag("reg4", 1,1);
        Item large1 = new LargeBag("large1", 1,1);
        Item large2 = new LargeBag("large2", 1,1);

        // rooms
        gameManager.addRoom(room1);
        gameManager.addRoom(room2);
        gameManager.addRoom(room3);
        gameManager.connectRooms(room1, room2, Direction.NORTH);
        gameManager.connectRooms(room2, room3, Direction.NORTH);

        //putting bags in rooms
        gameManager.addItem(room1,large1);
        gameManager.addItem(room1,reg1);
        gameManager.addItem(room2,reg2);
        gameManager.addItem(room2,reg3);
        gameManager.addItem(room3,reg4);
        gameManager.addItem(room3,large2);

        //THE GAME
        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);
        gameManager.useItem(large1);
        gameManager.pickUpItem(reg1);
        gameManager.movePlayer(Direction.NORTH);
        gameManager.pickUpItem(reg2);
        gameManager.pickUpItem(reg3);
        gameManager.movePlayer(Direction.NORTH);
        gameManager.pickUpItem(reg4);
        gameManager.useItem(large2);

        System.out.println("game");
    }

    public static void test8() {
        // from regular bag to large bag
        GameManager gameManager = new GameManager();
        Player player = new Player("Player 1", 2);

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");

        Item reg1 = new LargeBag("reg1", 1,1);
        Item reg2 = new LargeBag("reg2", 1,1);
        gameManager.addRoom(room1);
        gameManager.addItem(room1, reg1);
        gameManager.addItem(room1,reg2);
        gameManager.addPlayer(player);
        gameManager.startPlayer(room1);
        gameManager.useItem(reg1);
        gameManager.pickUpItem(reg2);
        System.out.println("GAME ENDED");
    }
}
