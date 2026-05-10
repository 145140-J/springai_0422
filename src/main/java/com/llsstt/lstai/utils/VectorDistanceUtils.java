package com.llsstt.lstai.utils;

/**
 * 向量距离计算工具类
 * 支持 float 类型向量的欧氏距离和余弦距离计算
 */
public class VectorDistanceUtils {

    /**
     * 计算两个 float 向量之间的欧氏距离
     *
     * @param vector1 第一个向量
     * @param vector2 第二个向量
     * @return 欧氏距离
     * @throws IllegalArgumentException 如果向量维度不匹配或为空
     */
    public static double euclideanDistance(float[] vector1, float[] vector2) {
        validateVectors(vector1, vector2);

        double sum = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            double diff = vector1[i] - vector2[i];
            sum += diff * diff;
        }

        return Math.sqrt(sum);
    }

    /**
     * 计算两个 float 向量之间的欧氏距离（返回 float）
     *
     * @param vector1 第一个向量
     * @param vector2 第二个向量
     * @return 欧氏距离
     * @throws IllegalArgumentException 如果向量维度不匹配或为空
     */
    public static float euclideanDistanceFloat(float[] vector1, float[] vector2) {
        return (float) euclideanDistance(vector1, vector2);
    }

    /**
     * 计算两个 float 向量之间的余弦相似度
     *
     * @param vector1 第一个向量
     * @param vector2 第二个向量
     * @return 余弦相似度，范围[-1, 1]
     * @throws IllegalArgumentException 如果向量维度不匹配或为空
     * @throws ArithmeticException 如果向量模为零
     */
    public static double cosineSimilarity(float[] vector1, float[] vector2) {
        validateVectors(vector1, vector2);

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            norm1 += vector1[i] * vector1[i];
            norm2 += vector2[i] * vector2[i];
        }

        norm1 = Math.sqrt(norm1);
        norm2 = Math.sqrt(norm2);

        if (norm1 == 0 || norm2 == 0) {
            throw new ArithmeticException("向量模不能为零");
        }

        return dotProduct / (norm1 * norm2);
    }

    /**
     * 计算两个 float 向量之间的余弦相似度（返回 float）
     *
     * @param vector1 第一个向量
     * @param vector2 第二个向量
     * @return 余弦相似度，范围[-1, 1]
     * @throws IllegalArgumentException 如果向量维度不匹配或为空
     * @throws ArithmeticException 如果向量模为零
     */
    public static float cosineSimilarityFloat(float[] vector1, float[] vector2) {
        return (float) cosineSimilarity(vector1, vector2);
    }

    /**
     * 计算两个 float 向量之间的余弦距离
     * 余弦距离 = 1 - 余弦相似度
     *
     * @param vector1 第一个向量
     * @param vector2 第二个向量
     * @return 余弦距离，范围[0, 2]
     * @throws IllegalArgumentException 如果向量维度不匹配或为空
     * @throws ArithmeticException 如果向量模为零
     */
    public static double cosineDistance(float[] vector1, float[] vector2) {
        return 1.0 - cosineSimilarity(vector1, vector2);
    }

    /**
     * 计算两个 float 向量之间的余弦距离（返回 float）
     *
     * @param vector1 第一个向量
     * @param vector2 第二个向量
     * @return 余弦距离，范围[0, 2]
     * @throws IllegalArgumentException 如果向量维度不匹配或为空
     * @throws ArithmeticException 如果向量模为零
     */
    public static float cosineDistanceFloat(float[] vector1, float[] vector2) {
        return (float) cosineDistance(vector1, vector2);
    }

    /**
     * 批量计算目标向量与多个向量的欧氏距离
     *
     * @param target 目标向量
     * @param vectors 多个向量数组
     * @return 欧氏距离数组
     */
    public static double[] batchEuclideanDistance(float[] target, float[][] vectors) {
        if (target == null || vectors == null) {
            throw new IllegalArgumentException("输入参数不能为null");
        }

        double[] distances = new double[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            distances[i] = euclideanDistance(target, vectors[i]);
        }
        return distances;
    }

    /**
     * 批量计算目标向量与多个向量的余弦相似度
     *
     * @param target 目标向量
     * @param vectors 多个向量数组
     * @return 余弦相似度数组
     */
    public static double[] batchCosineSimilarity(float[] target, float[][] vectors) {
        if (target == null || vectors == null) {
            throw new IllegalArgumentException("输入参数不能为null");
        }

        double[] similarities = new double[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            similarities[i] = cosineSimilarity(target, vectors[i]);
        }
        return similarities;
    }

    /**
     * 批量计算目标向量与多个向量的余弦距离
     *
     * @param target 目标向量
     * @param vectors 多个向量数组
     * @return 余弦距离数组
     */
    public static double[] batchCosineDistance(float[] target, float[][] vectors) {
        if (target == null || vectors == null) {
            throw new IllegalArgumentException("输入参数不能为null");
        }

        double[] distances = new double[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            distances[i] = cosineDistance(target, vectors[i]);
        }
        return distances;
    }

    /**
     * 找到欧氏距离最近的向量
     *
     * @param target 目标向量
     * @param vectors 候选向量数组
     * @return 最近向量的索引
     */
    public static int findNearestByEuclidean(float[] target, float[][] vectors) {
        validateVectorArray(vectors);

        int nearestIndex = 0;
        double minDistance = euclideanDistance(target, vectors[0]);

        for (int i = 1; i < vectors.length; i++) {
            double distance = euclideanDistance(target, vectors[i]);
            if (distance < minDistance) {
                minDistance = distance;
                nearestIndex = i;
            }
        }

        return nearestIndex;
    }

    /**
     * 找到余弦相似度最高的向量（最相似）
     *
     * @param target 目标向量
     * @param vectors 候选向量数组
     * @return 最相似向量的索引
     */
    public static int findMostSimilarByCosine(float[] target, float[][] vectors) {
        validateVectorArray(vectors);

        int mostSimilarIndex = 0;
        double maxSimilarity = cosineSimilarity(target, vectors[0]);

        for (int i = 1; i < vectors.length; i++) {
            double similarity = cosineSimilarity(target, vectors[i]);
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                mostSimilarIndex = i;
            }
        }

        return mostSimilarIndex;
    }

    /**
     * 找到余弦距离最小的向量（最相似）
     *
     * @param target 目标向量
     * @param vectors 候选向量数组
     * @return 最相似向量的索引
     */
    public static int findNearestByCosineDistance(float[] target, float[][] vectors) {
        validateVectorArray(vectors);

        int nearestIndex = 0;
        double minDistance = cosineDistance(target, vectors[0]);

        for (int i = 1; i < vectors.length; i++) {
            double distance = cosineDistance(target, vectors[i]);
            if (distance < minDistance) {
                minDistance = distance;
                nearestIndex = i;
            }
        }

        return nearestIndex;
    }

    /**
     * 验证两个向量
     */
    private static void validateVectors(float[] vector1, float[] vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("向量不能为null");
        }

        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("向量维度必须相同: " + vector1.length + " vs " + vector2.length);
        }

        if (vector1.length == 0) {
            throw new IllegalArgumentException("向量不能为空");
        }
    }

    /**
     * 验证向量数组
     */
    private static void validateVectorArray(float[][] vectors) {
        if (vectors == null || vectors.length == 0) {
            throw new IllegalArgumentException("候选向量数组不能为空");
        }
    }
}
