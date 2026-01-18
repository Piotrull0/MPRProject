package com.example.project.config;

import com.example.project.model.*;
import com.example.project.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final ExerciseRepository exerciseRepository;
    private final GymClassRepository gymClassRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    public DataLoader(MemberRepository memberRepository,
            TrainerRepository trainerRepository,
            ExerciseRepository exerciseRepository,
            GymClassRepository gymClassRepository,
            WorkoutPlanRepository workoutPlanRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
        this.exerciseRepository = exerciseRepository;
        this.gymClassRepository = gymClassRepository;
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (memberRepository.count() > 0) {
            logger.info("Database already contains data, skipping DataLoader.");
            return;
        }

        logger.info("Starting to load sample data...");

        Member member1 = new Member("John", "Doe", "john.doe@example.com");
        Membership membership1 = new Membership("Premium", LocalDate.now(), LocalDate.now().plusMonths(12));
        membership1.setMember(member1);
        member1.setMembership(membership1);

        Member member2 = new Member("Jane", "Smith", "jane.smith@example.com");
        Membership membership2 = new Membership("Basic", LocalDate.now(), LocalDate.now().plusMonths(6));
        membership2.setMember(member2);
        member2.setMembership(membership2);

        Member member3 = new Member("Mike", "Johnson", "mike.j@example.com");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Trainer trainer1 = new Trainer("Sarah Connor", "Strength Training", "10 years of experience in powerlifting");
        Trainer trainer2 = new Trainer("Bruce Lee", "Martial Arts & Cardio", "Expert in HIIT and functional training");

        trainerRepository.save(trainer1);
        trainerRepository.save(trainer2);

        Exercise exercise1 = new Exercise("Bench Press", 10, "Strength", "Chest");
        Exercise exercise2 = new Exercise("Squat", 12, "Strength", "Legs");
        Exercise exercise3 = new Exercise("Deadlift", 8, "Strength", "Back");
        Exercise exercise4 = new Exercise("Pull-ups", 8, "Strength", "Back");
        Exercise exercise5 = new Exercise("Running", 1, "Cardio", "Cardio");

        exerciseRepository.save(exercise1);
        exerciseRepository.save(exercise2);
        exerciseRepository.save(exercise3);
        exerciseRepository.save(exercise4);
        exerciseRepository.save(exercise5);

        GymClass class1 = new GymClass("Morning Yoga", LocalDateTime.now().plusDays(1).withHour(8).withMinute(0), 20);
        GymClass class2 = new GymClass("HIIT Training", LocalDateTime.now().plusDays(2).withHour(18).withMinute(0), 15);
        GymClass class3 = new GymClass("Spin Class", LocalDateTime.now().plusDays(3).withHour(19).withMinute(0), 25);

        gymClassRepository.save(class1);
        gymClassRepository.save(class2);
        gymClassRepository.save(class3);

        WorkoutPlan plan1 = new WorkoutPlan("Beginner Strength", "Easy", "Build foundation strength");
        plan1.setTrainer(trainer1);
        plan1.getExercises().add(exercise1);
        plan1.getExercises().add(exercise2);

        WorkoutPlan plan2 = new WorkoutPlan("Advanced Powerlifting", "Hard", "Increase max lifts");
        plan2.setTrainer(trainer1);
        plan2.getExercises().add(exercise1);
        plan2.getExercises().add(exercise2);
        plan2.getExercises().add(exercise3);

        WorkoutPlan plan3 = new WorkoutPlan("Cardio Blast", "Medium", "Improve cardiovascular fitness");
        plan3.setTrainer(trainer2);
        plan3.getExercises().add(exercise5);
        plan3.getExercises().add(exercise4);

        workoutPlanRepository.save(plan1);
        workoutPlanRepository.save(plan2);
        workoutPlanRepository.save(plan3);

        logger.info("Sample data loaded successfully!");
    }
}
