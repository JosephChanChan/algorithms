#include <stdio.h>
#include <stdlib.h>

struct Node{
	Node* next;
	int data;
};

void display(Node* head){
	Node* node = head->next;
	while(NULL != node->next){
		printf("%d ",node->data);
		node = node->next;
	}
}

void insertToHead(int val,Node* head){
	if(NULL == head){
		return;
	}

	Node* node = (Node*) malloc(sizeof(Node));
	node->data = val;

	if(NULL != head->next){
		node->next = head->next;
	}

	head->next = node;
}

int getListLen(Node* head){
	if(NULL == head){
		return 0;
	}

	Node* node = head->next;
	int i = 0;
	while(NULL != node->next){
		node = node->next;
		i++;
	}

	return i;
}

//按数组下标删除
void deleteAtIndex(int index,Node* head){
	if(index < 0 || 
		index > getListLen(head)-1){
		return;
	}

	index--;
	Node* before = head->next;
	while(NULL != before && index > 0){
		before = before->next;
		index--;
	}

	Node* deleted = before->next;
	before->next = deleted->next;
	deleted->next = NULL;
}

//按数组下标插入
void insertBeforeIndex(int index,int val,Node* head){
	if(index < 0 || 
		index > getListLen(head)-1){
		return;
	}

	if(0 == index){
		insertToHead(val,head);
		return;
	}

	Node* node = (Node*) malloc(sizeof(Node));

	Node* item = head->next;
	index--;
	while(NULL != item && index > 0){
		item = item->next;
		index--;
	}

	node->data = val;
	node->next = item->next;
	item->next = node;
}

void insertAfterIndex(int index,int val,Node* head){
	if(index < 0 || 
		index > getListLen(head)-1){
		return;
	}

	Node* node = (Node*) malloc(sizeof(Node));

	Node* item = head->next;
	while(NULL != item && index > 0){
		item = item->next;
		index--;
	}

	node->data = val;
	node->next = item->next;
	item->next = node;
}

int main(){
	Node* head = (Node*) malloc(sizeof(Node));
	Node* tail = (Node*) malloc(sizeof(Node));
	head->next = tail;
	tail->next = NULL;
	
	insertToHead(4,head);
	insertToHead(2,head);
	insertToHead(1,head);

	insertBeforeIndex(2,3,head);

	insertBeforeIndex(0,0,head);

	//演示超出数组长度插入
	insertAfterIndex(getListLen(head),-5,head);

	//演示低于起始位插入
	insertBeforeIndex(-1,-1,head);

	display(head);

	printf("\n");

	insertAfterIndex(2,10,head);
	insertAfterIndex(4,22,head);
	insertAfterIndex(6,99,head);

	display(head);

	printf("\n");

	deleteAtIndex(3,head);

	display(head);

	printf("\n");

	deleteAtIndex(getListLen(head)-1,head);

	display(head);

	return 0;
}