package com.example.a75hardchallenge.Models

import com.example.a75hardchallenge.R


class Constants {

    companion object {
        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val basicsquats = ExerciseModel(
                1, "Squats", R.drawable.squatt2, false, false
            )
            exerciseList.add(basicsquats)

            val bicepcurls = ExerciseModel(
                2, "Bicep Curls", R.drawable.bicepcurls2, false, false
            )
            exerciseList.add(bicepcurls)

            val chairdips = ExerciseModel(
                3, "Neck Rolls", R.drawable.neckrolls, false, false
            )
            exerciseList.add(chairdips)

            val crunches = ExerciseModel(
                4, "Crunches", R.drawable.crunches2, false, false
            )
            exerciseList.add(crunches)

            val deadbug = ExerciseModel(
                5, "Plank Shoulder Taps", R.drawable.plankshouldertaps, false, false
            )
            exerciseList.add(deadbug)

            val glutebridges = ExerciseModel(
                6, "Back Stretch", R.drawable.backstretch, false, false
            )
            exerciseList.add(glutebridges)

            val kneelingpushups = ExerciseModel(
                7, "Mountain Climber", R.drawable.mountainclimber, false, false
            )
            exerciseList.add(kneelingpushups)

            val kneelingsideplanks = ExerciseModel(
                8, "Wrist Curls", R.drawable.wristcurls, false, false
            )
            exerciseList.add(kneelingsideplanks)

            val laterallegraises = ExerciseModel(
                9, "Lateral Leg Swings", R.drawable.laterallegswings, false, false
            )
            exerciseList.add(laterallegraises)

            val lowerbackshit = ExerciseModel(
                10, "Pilates", R.drawable.pilateshundred, false, false
            )
            exerciseList.add(lowerbackshit)

            val lunges = ExerciseModel(
                11, "Lunge Twist", R.drawable.lungetwist, false, false
            )
            exerciseList.add(lunges)

            val situps = ExerciseModel(
                12, "Standing Side Bend", R.drawable.standingsidebend, false, false
            )
            exerciseList.add(situps)

            return exerciseList
        }

        fun defaultPushWorkoutList(): ArrayList<ExerciseModel> {
            val pushWorkoutList = ArrayList<ExerciseModel>()
            val jumping_jacks = ExerciseModel(
                13, "Jumping Jacks", R.drawable.jumping_jacks_final, false, false
            )
            pushWorkoutList.add(jumping_jacks)
            val pushups = ExerciseModel(
                14, "Push Ups", R.drawable.pushups_final, false, false
            )
            pushWorkoutList.add(pushups)
            val kneepushups =
                ExerciseModel(15, "Knee Push Ups", R.drawable.knee_pushup_final, false, false)
            pushWorkoutList.add(kneepushups)
            val tricepdips =
                ExerciseModel(16, "Tricep Dips", R.drawable.tricep_dip_final, false, false)
            pushWorkoutList.add(tricepdips)
            val declinepushup = ExerciseModel(
                17, "Decline Push Ups", R.drawable.pushup_rotation_final, false, false
            )
            pushWorkoutList.add(declinepushup)
            val tricepkickbacks = ExerciseModel(
                18, "Tricep KickBacks", R.drawable.tricep_kickbacks_final, false, false
            )
            pushWorkoutList.add(tricepkickbacks)
            val pushuprotation = ExerciseModel(
                19, "Push Up Rotation", R.drawable.pushup_rotation_final, false, false
            )
            pushWorkoutList.add(pushuprotation)


            val lateralRaises =
                ExerciseModel(20, "Lateral Raises", R.drawable.lateral_raises_final, false, false)
            pushWorkoutList.add(lateralRaises)

            return pushWorkoutList


        }

        fun defaultPullWorkoutList(): ArrayList<ExerciseModel> {
            val pullWorkoutList = ArrayList<ExerciseModel>()
            val jumpingjacks = ExerciseModel(
                21, "Jumping Jacks", R.drawable.jumping_jacks_final, false, false
            )
            pullWorkoutList.add(jumpingjacks)
            val neckrolls =
                ExerciseModel(22, "Neck Rolls", R.drawable.neck_rolls_finals, false, false)
            pullWorkoutList.add(neckrolls)
            val pushups = ExerciseModel(
                23, "Push Ups", R.drawable.pushups_final, false, false
            )
            pullWorkoutList.add(pushups)
            val kneepushups =
                ExerciseModel(24, "Knee Push Ups", R.drawable.knee_pushup_final, false, false)
            pullWorkoutList.add(kneepushups)
            val hyperextension =
                ExerciseModel(25, "Hyper Extension", R.drawable.hyperextension_final, false, false)
            pullWorkoutList.add(hyperextension)
            val bicepcurl =
                ExerciseModel(26, "Bicep Curl", R.drawable.bicep_curls_final, false, false)
            pullWorkoutList.add(bicepcurl)
            val dumbellRows =
                ExerciseModel(27, "Dumbell Rows", R.drawable.dumbell_rows_final, false, false)
            pullWorkoutList.add(dumbellRows)
            val hindupushups =
                ExerciseModel(28, "Hindu Push Ups", R.drawable.hindu_push_ups_final, false, false)
            pullWorkoutList.add(hindupushups)
            val pushuprotation = ExerciseModel(
                29, "Push Up Rotation", R.drawable.pushup_rotation_final, false, false
            )
            pullWorkoutList.add(pushuprotation)
            val spidermanpushup = ExerciseModel(
                29, "Spiderman PushUp", R.drawable.spiderman_pushups_final, false, false
            )
            pullWorkoutList.add(spidermanpushup)
            val shouldertaps =
                ExerciseModel(30, "Shoulder Taps", R.drawable.shoulder_taps_finals, false, false)
            pullWorkoutList.add(shouldertaps)
            return pullWorkoutList
        }

        fun defaultLegWorkoutList(): ArrayList<ExerciseModel> {
            val legsworkoutList = ArrayList<ExerciseModel>()
            val jumpingjacks =
                ExerciseModel(31, "Squats", R.drawable.jumping_jacks_final, false, false)
            legsworkoutList.add(jumpingjacks)

            val squats = ExerciseModel(32, "Squats", R.drawable.squats_final, false, false)
            legsworkoutList.add(squats)

            val calfRaises =
                ExerciseModel(33, "Calf Raises", R.drawable.calf_raises_final, false, false)
            legsworkoutList.add(calfRaises)

            val donkeyKicks =
                ExerciseModel(34, "Donkey Kicks", R.drawable.donkey_kicks_final, false, false)
            legsworkoutList.add(donkeyKicks)

            val legLateralRaises = ExerciseModel(
                35, "Leg Lateral Raises", R.drawable.leg_lateral_raises_final, false, false
            )
            legsworkoutList.add(legLateralRaises)

            val lunges = ExerciseModel(36, "Lunges", R.drawable.lunges_final, false, false)
            legsworkoutList.add(lunges)

            val lungeTwist =
                ExerciseModel(37, "Lunge Twist", R.drawable.lungetwist_final, false, false)
            legsworkoutList.add(lungeTwist)
            val planks = ExerciseModel(38, "Planks", R.drawable.planks_final, false, false)
            legsworkoutList.add(planks)
            val mountainclimber =
                ExerciseModel(39, "Mountain Climber", R.drawable.wallclimber_final, false, false)
            legsworkoutList.add(mountainclimber)

            val crunches = ExerciseModel(40, "Crunches", R.drawable.crunches_final, false, false)
            legsworkoutList.add(crunches)

            val catcowpose =
                ExerciseModel(41, "Cat & Cow Pose", R.drawable.crunches_final, false, false)
            legsworkoutList.add(catcowpose)


            return legsworkoutList
        }

        fun defaultOutdoorWorkout(): ArrayList<ExerciseModel> {
            val OutdoorWorkoutList = ArrayList<ExerciseModel>()
            val jumpingjacks =
                ExerciseModel(42, "Jumping Jacks", R.drawable.jumping_jacks_final, false, false)
            OutdoorWorkoutList.add(jumpingjacks)
            val mountainclimber =
                ExerciseModel(43, "Walk Downs", R.drawable.wallclimber_final, false, false)
            OutdoorWorkoutList.add(mountainclimber)
            val pushup = ExerciseModel(44, "Push Ups", R.drawable.pushups_final, false, false)
            OutdoorWorkoutList.add(pushup)
            val highKnees = ExerciseModel(45, "Jog In Place", R.drawable.highknees, false, false)
            OutdoorWorkoutList.add(highKnees)
            val shoulderTaps =
                ExerciseModel(46, "Planks", R.drawable.shoulder_taps_finals, false, false)
            OutdoorWorkoutList.add(shoulderTaps)
            val mountainClimber =
                ExerciseModel(47, "Mountain Climber", R.drawable.wallclimber_final, false, false)
            OutdoorWorkoutList.add(mountainClimber)
            val pushuprotation = ExerciseModel(
                48, "Pushup and Rotation", R.drawable.pushup_rotation_final, false, false
            )
            OutdoorWorkoutList.add(pushuprotation)

            val skipping = ExerciseModel(49, "Skipping", R.drawable.skipping_final, false, false)
            OutdoorWorkoutList.add(skipping)


            return OutdoorWorkoutList
        }
        fun defaultYogaWorkout():ArrayList<ExerciseModel>{
            val yogaWorkoutList=ArrayList<ExerciseModel>()
            val cobrapose=ExerciseModel(50,"Cobra Pose", R.drawable.bhujhangasana,false,false)
            yogaWorkoutList.add(cobrapose)
            val kneecircles=ExerciseModel(51,"Knee Circles", R.drawable.kneecircles,false,false)
            yogaWorkoutList.add(kneecircles)
            val childPose=ExerciseModel(52,"Child's Pose", R.drawable.child,false,false)
            yogaWorkoutList.add(childPose)
            val layinglegStretch=ExerciseModel(53,"Laying Leg Stretch",
                R.drawable.layinglegstretch,false,false)
            yogaWorkoutList.add(layinglegStretch)
            val shoulderCircles=ExerciseModel(54,"Shoulder Circles",
                R.drawable.shoulder_circles,false,false)
            yogaWorkoutList.add(shoulderCircles)
            val layinglowerBackGluteStretch=ExerciseModel(55,"Lower Back Glute Stretch",
                R.drawable.laying_lowerback_hamstringstretch,false,false)
            yogaWorkoutList.add(layinglowerBackGluteStretch)
            val tpress=ExerciseModel(55,"Laying T-press", R.drawable.tpress,false,false)
            yogaWorkoutList.add(tpress)
            val side_Stretch=ExerciseModel(56,"Side Stretch", R.drawable.side_stretch,false,false)
            yogaWorkoutList.add(side_Stretch)

            return yogaWorkoutList
        }
    }
}