

export interface Lesson {
    id: number;
    description: string;
    duration: string;
    seqNo: number;
    courseId: number;
    videoId: string;
}

export function sortLessonBySeqNo(l1: Lesson, l2: Lesson) {
    return l1.seqNo - l2.seqNo;
}
