package com.autovaluetutorial.builderdemo;

import com.google.auto.value.AutoValue;

class Student {
	private int age;
	private String name;
	private Student(Builder builder) {
		this.age = builder.age;
		this.name = builder.name;
	}
	public String getName(){
		return name;
	}
	public int getAge() {
		return age;
	}

	public static Builder builder() {
		return new Builder();
	}
	static class Builder {
		private int age;
		private String name;
		
		public void setAge(int age) {
			this.age = age;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public Student build() {
			return new Student(this);
		}
	}
}

@AutoValue
abstract class StudentAuto {
	abstract String getName();
	abstract int getAge();
//	public static StudentAuto create(String name, int age) {
//		return new AutoValue_StudentAuto(name, age);
//	}

	public static Builder builder() {
		return new AutoValue_StudentAuto.Builder();
	};
	@AutoValue.Builder
	abstract static class Builder {
		abstract Builder setName(String name);
		abstract Builder setAge(int age);
		abstract StudentAuto build();
	}
}

class Demo {
	public static void main(String[] args) {
		Student.Builder builder = Student.builder();
		builder.setName("meng");
		builder.setAge(18);
		Student meng = builder.build();
		System.out.println(
				meng.getName() + ":" + meng.getAge()
		);

		StudentAuto honghua = StudentAuto.builder().setName("h").setAge(40).build();
		System.out.println(honghua);
	}
}
