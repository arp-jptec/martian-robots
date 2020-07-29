public class MartianRobots {

    private int[][] grid;

    private MartianRobots(int upperX, int upperY){
        grid = new int[++upperX][++upperY];
    }

    class RobotPosition{
        int x;
        int y;
        Direction direction;

        RobotPosition(int x, int y, Direction direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }


    enum Direction {
        NORTH('N',WEST,EAST,0,1),
        EAST('E',NORTH,SOUTH,1,0),
        SOUTH('S',EAST,WEST,0,-1),
        WEST('W',SOUTH,NORTH,-1,0);

        char label;
        Direction left;
        Direction right;
        int xMove;
        int yMove;

        private Direction(char label, Direction left, Direction right,int xMove, int yMove){
            this.label = label;
            this.left = left;
            this.right = right;
            this.xMove = xMove;
            this.yMove = yMove;
        }

        public Direction getDirectionByLabel(char label){
            for(Direction d : values())
                if(d.label == label)
                    return d;
            throw new IllegalArgumentException("Invalid direction "+label);
        }
    }



}