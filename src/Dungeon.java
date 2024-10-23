public class Dungeon {
    public void printDungeon() {
        StringBuilder dungeon = new StringBuilder();
        dungeon.append("#####################\n");
        for (int i = 0; i < 5; i++) {
            dungeon.append("#                   #\n");
        }
        dungeon.append("#####################\n");
        System.out.println(dungeon.toString());
    }
}
