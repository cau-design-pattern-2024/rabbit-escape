//package rabbitescape.engine;
//
//public class StrongJumpingRabbit extends StrongRabbit {
//
//    public StrongJumpingRabbit(Rabbit rabbit) {
//        super(rabbit);
//    }
//
//    @Override
//    public void step(World world) {
//        super.step(world); // 기본 동작 수행
//        enhanceJumping(world); // 점프 동작 강화
//    }
//
//    private void enhanceJumping(World world) {
//        System.out.println("StrongJumpingRabbit is jumping higher!");
//    }
//
//    @Override
//    public String overlayText() {
//        return super.overlayText() + " (Jumping)";
//    }
//}
