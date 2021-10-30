

import java.util.Arrays;
class SimpleHashSet {
        int[] head;
        int[] next;
        int[] keys;
        int headNum;
        int cnt = 1;

                SimpleHashSet(int headNum, int maxSize) {
                    this.headNum = headNum;
                     head = new int [headNum];
                   next = new int [maxSize + 1];
                     keys = new int [maxSize + 1];
               }

                 boolean add(int x) {
                  if (this.contains(x))
                          return false;
                  int h = index(hash(x));
                    next[cnt] = head[h];
                    keys[cnt] = x;
                     head[h] = cnt++;
                     return true;
               }


                 boolean contains(int x) {
                   int h = index(hash(x));
                  for (int i = head[h]; i != 0; i = next[i])
                           if (keys[i] == x)
                             return true;
                  return false;
             }

                 /* хэш-функция (для других типов следует изменить) */
                 int hash(int x) {
                   return (x >> 15) ^ x;
              }

                 /* возвращает номер головы по значению хэш-функции */
                int index(int hash) {
                    return Math.abs(hash) % headNum;
               }
     }

            class OpenHashSet {
         int FREE = Integer.MIN_VALUE;
         int size;
        int[] keys;

                OpenHashSet(int size) {
                     this.size = Math.max(3 * size / 2, size) + 1;
                   keys = new int [this.size];
                   Arrays.fill(keys, FREE);
               }


               boolean add(int x) {
                    for (int i = index(hash(x)); ; i++) {
                            if (i == size) i = 0;
                           if (keys[i] == x) return false;
                            if (keys[i] == FREE) {
                                   keys[i] = x;
                                  return true;
                                 }
                      }
               }


                boolean contains(int x) {
                  for (int i = index(hash(x)); ; i++) {
                            if (i == size) i = 0;
                           if (keys[i] == x) return true;
                           if (keys[i] == FREE) return false;
                        }
           }

                int hash(int x) {
                 return (x >> 15) ^ x;
             }

               int index(int hash) {
                 return Math.abs(hash) % size;
              }
    }

       class OpenHashMap {
        int FREE = Integer.MIN_VALUE;
        int size;
        int[] keys;
        int[] values;

               OpenHashMap(int size) {
                   this.size = Math.max(3 * size / 2, size) + 1;
                  keys = new int [this.size];
                     values = new int [this.size];
                   Arrays.fill(keys, FREE);
              }


                void put(int x, int y) {
                    for (int i = index(hash(x)); ; i++) {
                          if (i == size) i = 0;
                             if (keys[i] == FREE)
                                  keys[i] = x;
                            if (keys[i] == x) {
                                  values[i] = y;
                                  return;
                             }
                        }
            }


             int get(int x) {
                    for (int i = index(hash(x)); ; i++) {
                           if (i == size) i = 0;
                            if (keys[i] == FREE) throw new RuntimeException("No such key!");
                        if (keys[i] == x) return values[i];
                        }
                }

               boolean containsKey(int x) {
                    for (int i = index(hash(x)); ; i++) {
                          if (i == size) i = 0;
                         if (keys[i] == FREE) return false;
                        if (keys[i] == x) return true;
                       }
             }


               int hash(int x) {
                  return (x >> 15) ^ x;
                }

               int index(int hash) {
                 return Math.abs(hash) % size;
               }
     }

           class FastHashMap {
      int HEAD_NUM;
        int MASK;

              int[] head;
         int[] next;
       int[] keys;
        int[] values;
        int cnt = 1;

              FastHashMap(int degree, int maxSize) {
                HEAD_NUM = 1 << degree;
               MASK = HEAD_NUM - 1;
                  head = new int [HEAD_NUM];
                next = new int [maxSize + 1];
                 keys = new int [maxSize + 1];
                   values = new int [maxSize + 1];
              }

                 void put(int x, int y) {
                    int h = index(x);
                    for (int i = head[h]; i != 0; i = next[i])
                            if (keys[i] == x) {
                                 values[i] = y;
                                 return;
                             }
                     next[cnt] = head[h];
                    keys[cnt] = x;
                     values[cnt] = y;
                    head[h] = cnt++;
                }

                 int get(int x) {
                     int h = index(x);
                     for (int i = head[h]; i != 0; i = next[i])
                             if (keys[i] == x)
                               return values[i];
                   throw new RuntimeException("No such key!");
             }

                 boolean containsKey(int x) {
                    int h = index(x);
                   for (int i = head[h]; i != 0; i = next[i])
                         if (keys[i] == x)
                               return true;
                   return false;
               }

              int index(int x) {
                  return Math.abs((x >> 15) ^ x) & MASK;
              }
     }