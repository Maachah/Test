package Maachah;

import java.util.Scanner;

public class Tms {
	private Teacher[] teac;	
	private int index;		

	public Tms(){
		this.teac = new Teacher[4];
		this.index = 0;
	}
	
	public void save(Teacher tea){
		if(index >= teac.length){
			Teacher[] aa = new Teacher[teac.length+3];
			System.arraycopy(teac,0,aa,0,index);
			teac = aa;
		}
		teac[index++] = tea;
	}
	
	public Teacher[] findAll(){
		Teacher[] aa = new Teacher[index];
		System.arraycopy(teac,0,aa,0,index);
		return aa;
	}
	
	public Teacher findById(long id){
		int num = findIndexById(id);
		return num==-1?null:teac[num];
	}

	public int findIndexById(long id){
		int num = -1;
		for(int i=0;i<index;i++){
			if(teac[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}


	public void deleteById(long id){
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			teac[i] = teac[i+1];
		}
		teac[--index] = null;
	}
	
	public void update(Teacher newTea){
		for(int i=0;i<index;i++){
			if(teac[i].getId() == newTea.getId()){
				teac[i].setName(newTea.getName());
				teac[i].setCourse(newTea.getCourse());
			}
		}
	}

	
	public void menu(){
		System.out.println("***********��ʦ��Ϣ����ϵͳ***********");
		System.out.println("*1. ��ѯ������ʦ��Ϣ");
		System.out.println("*2. �����ʦ��Ϣ");
		System.out.println("*3. ɾ����ʦ��Ϣ");
		System.out.println("*4. �鿴��ʦ��Ϣ");
		System.out.println("*5. ������ʦ��Ϣ");
		System.out.println("*help. ����");
		System.out.println("*exit. �˳�");
		System.out.println("**************************************");
	}
	
	public static void main(String[] args){
		Tms tms = new Tms();	
		Scanner sc = new Scanner(System.in);
		tms.menu();
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			String option = sc.nextLine();
			switch(option){
				case "1":{
					System.out.println("������������ʦ����Ϣ��");
					Teacher[] arr = tms.findAll();
					for(Teacher tea : arr){
						System.out.println(tea);
					}
					System.out.println("�ܼ� "+tms.index+"��");
					break;
				}
				case "2":{
					while(true){
						System.out.println("��������ʦ��Ϣ��id#name#course���������롾break���������˵�");
						String teaStr = sc.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						String course = teaArr[2];
						Teacher tea = new Teacher(id,name,course);
						tms.save(tea);
						System.out.println("��ӳɹ���");
						
					}
					break;
				}
				case "3":{
					while(true){
						System.out.println("������Ҫɾ����ʦ�ġ�id���������롾break��������Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("����ʦ�����ڣ�");
							continue;
						}
						tms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}

					break;
				}
				case "4":{
					while(true){
						System.out.println("������Ҫ������ʦ�ġ�id���������롾break��������Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("����ʦ�����ڣ�");
							continue;
						}
						System.out.println(tea);
					}
					break;
				}
				case "5":{
					while(true){
						System.out.println("������Ҫ�޸���ʦ�ġ�id���������롾break��������Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("��Ҫ�޸ĵ���ʦ��Ϣ�����ڣ�");
							continue;
						}

						System.out.println("��Ҫ�޸ĵ���ʦ��ϢΪ��"+tea);
						System.out.println("���������ʦ������Ϣ��name#course��");
						String teaStr = sc.nextLine();
						String[] teaArr = teaStr.split("#");
						String name = teaArr[0];
						String course = teaArr[1];
						Teacher newTea = new Teacher(id,name,course);
						tms.update(newTea);
						System.out.println("�޸ĳɹ�");
					}
					break;
				}
				case "exit":{
					System.out.println("bye bye!��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				}
				case "help":{
					tms.menu();
					break;
				}
				default:
					System.out.println("��������");
			}
		}
	}
}