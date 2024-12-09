//package rabbitescape.engine;
//
//import rabbitescape.engine.behaviours.*;
//
//import java.util.List;
//
//public class StrongRabbit extends Rabbit {
//
//    private final Rabbit wrappedRabbit; // 기존 Rabbit 객체를 감쌀 변수
//
//    // StrongRabbit은 기본 Rabbit을 받아서 데코레이터처럼 기능을 확장
//    public StrongRabbit(Rabbit rabbit) {
//        super(rabbit.x, rabbit.y, rabbit.dir, rabbit.type); // Rabbit의 생성자를 호출
//        this.wrappedRabbit = rabbit;
//    }
//
//    @Override
//    public void step(World world) {
//        // StrongRabbit은 기본 Rabbit의 동작을 확장함
//        // 예: 더 빠르게 행동하도록 만들기
//        super.step(world); // Rabbit의 기본 동작
//    }
//
//    @Override
//    public void calcNewState(World world) {
//        // StrongRabbit의 상태 계산을 확장
//        super.calcNewState(world); // 기본 Rabbit의 상태 계산
//    }
//
//    @Override
//    public String overlayText() {
//        // 기본 Rabbit의 텍스트를 그대로 사용하되, StrongRabbit의 텍스트를 덧붙일 수 있음
//        String baseOverlay = wrappedRabbit.overlayText();
//        return baseOverlay + " (Strong)";
//    }
//
//    @Override
//    public String stateName() {
//        // 기본 Rabbit의 상태 이름을 가져오고, 강한 Rabbit으로 표시
//        return wrappedRabbit.stateName() + " (Strong)";
//    }
//}
